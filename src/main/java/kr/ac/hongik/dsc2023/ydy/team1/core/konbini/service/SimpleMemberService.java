package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.*;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Member;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.MemberProfile;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.util.JWTMaker;
import kr.ac.hongik.dsc2023.ydy.team1.core.util.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleMemberService implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    @Transactional
    @Override
    public JoinResponse join(JoinRequest joinRequest) {
        validateEmail(joinRequest.getEmail());
        Member member = joinRequest.toEntity();
        member = memberRepository.saveAndFlush(member);
        String accessToken = JWTMaker.makeToken(member.getId(),false);
        String refreshToken = JWTMaker.makeToken(member.getId(),true);
        return new JoinResponse(accessToken,refreshToken);
    }
    private void validateEmail(String email){
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new IllegalArgumentException("잘못된 이메일입니다.");
        }
        if(memberRepository.existsByEmail(email)){
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }

    @Transactional
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Member member = validateLoginRequest(loginRequest);
        String accessToken = JWTMaker.makeToken(member.getId(),false);
        String refreshToken = JWTMaker.makeToken(member.getId(),true);
        return new LoginResponse(accessToken, refreshToken);
    }
    private Member validateLoginRequest(LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        password = SHA256.hash(password);
        return memberRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new SecurityException("이메일이나 패스워드가 잘못되었습니다."));
    }
    @Transactional
    @Override
    public PasswordChangeResponse changePassword(PasswordChangeRequest passwordChangeRequest) {
        String email = passwordChangeRequest.getEmail();
        String password = passwordChangeRequest.getPassword();
        password = SHA256.hash(password);
        String newPassword = passwordChangeRequest.getNewPassword();
        newPassword = SHA256.hash(newPassword);
        Member member = memberRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new SecurityException("이메일이나 패스워드가 잘못되었습니다."));
        member.updatePassword(newPassword);
        memberRepository.saveAndFlush(member);
        return new PasswordChangeResponse();
    }

    @Override
    public Response<List<KonbiniSearchItem>> getPersonalizeRecommendList(int memberID) {
        MemberProfile memberProfile = memberProfileRepository.findByMember_Id(memberID).orElse(null);
        boolean personalizeDataExists = memberProfile != null;
        if(!personalizeDataExists){
            List<KonbiniSearchItem> searchItems = promotionInfoRepository.findByPersonalizeData(memberID)
                    .stream()
                    .map(KonbiniSearchItem::new)
                    .collect(Collectors.toList());
            return KonbiniResponse.<List<KonbiniSearchItem>>builder()
                    .data(searchItems)
                    .message("기본 추천 데이터로 조회")
                    .build();
        }
        List<KonbiniSearchItem> searchItems = promotionInfoRepository.findByPersonalizeData(memberID)
                .stream()
                .map(KonbiniSearchItem::new)
                .collect(Collectors.toList());
        return KonbiniResponse.<List<KonbiniSearchItem>>builder()
                .data(searchItems)
                .message("맞춤 추천 데이터로 조회")
                .build();
    }
}
