package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.JoinResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.KonbiniSearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.LoginResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.PasswordChangeResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Member;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.MemberProfile;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.PersonalizeAlg;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.KonbiniItemRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.MemberRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.util.JWTMaker;
import kr.ac.hongik.dsc2023.ydy.team1.core.util.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SimpleMemberService implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    private final KonbiniItemRepository itemRepository;
    private final ObjectMapper mapper;

    @Transactional
    @Override
    public JoinResponse join(JoinRequest joinRequest) {
        validateEmail(joinRequest.getEmail());
        Member member = joinRequest.toEntity();
        MemberProfile memberProfile = new MemberProfile(member);
        memberProfile = memberProfileRepository.saveAndFlush(memberProfile);
        String accessToken = JWTMaker.makeToken(memberProfile.getMember().getId(), false);
        String refreshToken = JWTMaker.makeToken(memberProfile.getMember().getId(), true);
        return new JoinResponse(accessToken, refreshToken);
    }

    private void validateEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("잘못된 이메일입니다.");
        }
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }

    @Transactional
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Member member = validateLoginRequest(loginRequest);
        String accessToken = JWTMaker.makeToken(member.getId(), false);
        String refreshToken = JWTMaker.makeToken(member.getId(), true);
        return new LoginResponse(accessToken, refreshToken);
    }

    private Member validateLoginRequest(LoginRequest loginRequest) {
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
        if (!personalizeDataExists) {
            List<KonbiniSearchItem> searchItems = getBasicList();
            return makeRecommendResult(searchItems, "기본 추천 데이터로 조회");
        }
        PersonalizeAlg personalizeAlg = memberProfile.getPersonalizeAlg();
        if (personalizeAlg == PersonalizeAlg.CATEGORY_BASE) {
            List<KonbiniSearchItem> searchItems = getCategoryBasedList(memberID);
            return makeRecommendResult(searchItems, "맞춤 추천 데이터로 조회");
        }
        if (personalizeAlg == PersonalizeAlg.SUB_CATEGORY_BASE) {
            List<KonbiniSearchItem> searchItems = getSubCategoryBasedList(memberProfile);
            return makeRecommendResult(searchItems, "맞춤 추천 데이터로 조회");
        }
        if (personalizeAlg == PersonalizeAlg.RECENT_ACCESS_BASE) {
            List<KonbiniSearchItem> searchItems = getRecentAccessBasedList(memberProfile);
            return makeRecommendResult(searchItems, "맞춤 추천 데이터로 조회");
        }
        List<KonbiniSearchItem> searchItems = getBasicList();
        return makeRecommendResult(searchItems, "기본 추천 데이터로 조회");
    }

    private List<KonbiniSearchItem> getBasicList() {
        return promotionInfoRepository.findAllByItem_NameContainsAndStartDateGreaterThanEqualAndEndDateGreaterThanEqual(
                        "", LocalDate.now(), LocalDate.now())
                .stream()
                .map(KonbiniSearchItem::new)
                .collect(Collectors.toList());
    }

    private List<KonbiniSearchItem> getCategoryBasedList(int memberID) {
        return promotionInfoRepository.findByCategoryBasedPersonalizeData(memberID)
                .stream()
                .map(KonbiniSearchItem::new)
                .collect(Collectors.toList());
    }

    private List<KonbiniSearchItem> getSubCategoryBasedList(MemberProfile memberProfile) {
        Map<String, Object> recommendData = memberProfile.getRecommendData();
        List<String> categoryWithSub = new ArrayList<>(recommendData.keySet());
        categoryWithSub = categoryWithSub.stream()
                .filter(s -> !s.contentEquals("recent_items"))
                .sorted(Comparator.comparingInt(o -> (int) recommendData.get(o)))
                .limit(2)
                .map(s -> "$." + s.split("-")[1])
                .collect(Collectors.toList());

        if (categoryWithSub.isEmpty()) {
            return promotionInfoRepository.findAllByItem_NameContainsAndStartDateGreaterThanEqualAndEndDateGreaterThanEqual(
                            "", LocalDate.now(), LocalDate.now())
                    .stream()
                    .map(KonbiniSearchItem::new)
                    .collect(Collectors.toList());
        }

        if (categoryWithSub.size() == 1) {
            categoryWithSub.add(categoryWithSub.get(0));
        }

        return promotionInfoRepository.findBySubCategoryBasedPersonalizeData(categoryWithSub.get(0),
                        categoryWithSub.get(1), LocalDate.now().minusDays(1), LocalDate.now().minusDays(1))
                .stream()
                .map(KonbiniSearchItem::new)
                .collect(Collectors.toList());
    }

    private List<KonbiniSearchItem> getRecentAccessBasedList(MemberProfile memberProfile) {
        Map<String, Object> recommendData = memberProfile.getRecommendData();
        List<Map<String, Object>> tmp = (List<Map<String, Object>>) recommendData.getOrDefault("recent_items",
                new ArrayList<>());
        tmp = tmp.stream()
                .sorted(SimpleMemberService::compareByAccessTime)
                .limit(1)
                .collect(Collectors.toList());

        int recentItemID = (int) tmp.get(0).get("item_id");
        Item item = itemRepository.findById(Long.valueOf(recentItemID)).orElse(null);
        if (item == null) {
            return getBasicList();
        }
        Set<String> recentItemsSubCategories = item.getSubCategory().keySet();
        List<KonbiniSearchItem> konbiniSearchItems = recentItemsSubCategories.stream()
                .map(s -> promotionInfoRepository.findByRecentAccessBasedPersonalizeData(LocalDate.now(),
                        item.getCategory().name(), "$." + s))
                .flatMap(Collection::stream)
                .map(KonbiniSearchItem::new)
                .collect(Collectors.toList());
        Collections.shuffle(konbiniSearchItems);
        return konbiniSearchItems.stream()
                .limit(10)
                .collect(Collectors.toList());

//        List<PromotionInfo> promotionInfosByCategory = promotionInfoRepository.findByItem_CategoryAndStartDateAndEndDate(item.getCategory(), LocalDate.now(), LocalDate.now());
//        return promotionInfosByCategory.parallelStream()
//                .filter(p -> !p.getItem().equals(item))
//                .filter(p -> {
//                    Item promotionItem = p.getItem();
//                    Set<String> subCategories = promotionItem.getSubCategory().keySet();
//                    return subCategories.stream().anyMatch(recentItemsSubCategories::contains);
//                })
//                .sorted((o1, o2) -> {
//                    Set<String> o1SubCategory = o1.getItem().getSubCategory().keySet();
//                    Set<String> o2SubCategory = o2.getItem().getSubCategory().keySet();
//                    long o1MatchingCount = o1SubCategory.stream().filter(recentItemsSubCategories::contains).count();
//                    long o2MatchingCount = o2SubCategory.stream().filter(recentItemsSubCategories::contains).count();
//                    return -Long.compare(o1MatchingCount, o2MatchingCount);
//                })
//                .limit(10)
//                .map(KonbiniSearchItem::new)
//                .collect(Collectors.toList());
    }

    private static int compareByAccessTime(Map<String, Object> o1, Map<String, Object> o2) {
        String o1AccessTimeString = (String) o1.get("access_time");
        o1AccessTimeString = o1AccessTimeString.replace(" ", "T");
        LocalDateTime o1Time = LocalDateTime.parse(o1AccessTimeString);
        String o2AccessTimeString = (String) o2.get("access_time");
        o2AccessTimeString = o2AccessTimeString.replace(" ", "T");
        LocalDateTime o2Time = LocalDateTime.parse(o2AccessTimeString);
        return o2Time.compareTo(o1Time);
    }

    private static Response<List<KonbiniSearchItem>> makeRecommendResult(List<KonbiniSearchItem> searchItems,
                                                                         String message) {
        return Response.<List<KonbiniSearchItem>>builder()
                .data(searchItems)
                .message(message)
                .build();
    }
}
