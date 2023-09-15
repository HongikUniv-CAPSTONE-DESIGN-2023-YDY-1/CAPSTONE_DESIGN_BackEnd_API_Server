package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.recommend.PersonalizeInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Member;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.MemberProfile;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniItemRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SimpleRecommendService implements RecommendService {
    private final MemberRepository memberRepository;
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    private final MemberProfileRepository memberProfileRepository;
    @Transactional
    @Override
    public void recommend(PersonalizeInfo personalizeInfo) {
        int memberID = personalizeInfo.getMemberID();
        long promotionID = personalizeInfo.getPromotionID();
        Member member = memberRepository.findById(memberID).orElseThrow(() -> new NoSuchElementException("없는 회원입니다."));
        MemberProfile memberProfile = memberProfileRepository.findByMember_Id(memberID).orElse(new MemberProfile(member));
        PromotionInfo promotionInfo = promotionInfoRepository.findById(promotionID).orElse(null);

        boolean isInValidRecommend = promotionInfo == null;
        if (isInValidRecommend) {
            return;
        }
        Map<String,Object> recommendData = makeRecommendData(personalizeInfo);
        memberProfile.update(recommendData);
        memberProfileRepository.save(memberProfile);
    }
    public Map<String,Object> makeRecommendData(PersonalizeInfo personalizeInfo){
        Map<String,Object> recommendData = new HashMap<>();
        return recommendData;
    }
}
