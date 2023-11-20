package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Member;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.MemberProfile;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.event.PersonalizeEvent;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.ItemData;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * 개인화 과정 중 리뷰 사용 기록 기능이 구현된 추상 클래스. 이를 상속한 클래스에서 개인별 선호도 기록 기능을 구현해야 한다.
 */
@RequiredArgsConstructor
public abstract class AbstractPersonalizeService implements PersonalizeService {
    private final MemberRepository memberRepository;
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    private final MemberProfileRepository memberProfileRepository;

    @Transactional
    @Override
    public void recommend(PersonalizeEvent personalizeEvent) {
        int memberID = personalizeEvent.getMemberID();
        long promotionID = personalizeEvent.getPromotionID();
        Member member = memberRepository.findById(memberID).orElseThrow(() -> new NoSuchElementException("없는 회원입니다."));
        MemberProfile memberProfile = memberProfileRepository.findByMember_Id(memberID)
                .orElse(new MemberProfile(member));
        PromotionInfo promotionInfo = promotionInfoRepository.findById(promotionID).orElse(null);

        boolean isInValidRecommend = promotionInfo == null;
        if (isInValidRecommend) {
            return;
        }

        Map<String, Object> recommendData = updateRecentAccessInfo(memberProfile.getRecommendData(),
                promotionInfo.getItem());
        PreferStrong preferStrong = PreferStrong.STRONG;
        if (personalizeEvent.getRecommendType() == PersonalizeEvent.RecommendType.COMMENT_READ) {
            preferStrong = PreferStrong.WEEK;
        }
        recommendData = updatePreferInfo(promotionInfo.getItem(), recommendData, preferStrong);
        memberProfile.update(recommendData);
        memberProfileRepository.save(memberProfile);
    }

    /**
     * @param item                          선호하는 상품.
     * @param accessLogUpdatedRecommendData 엑세스 기록이 업데이트된 map.
     * @param preferStrong                  선호 강도.
     * @return 선호도 기록이 추가된 map. 입력으로 주어진 accessLogUpdatedRecommendData 객체에 항목이 추가되어 반환된다.
     */
    protected abstract Map<String, Object> updatePreferInfo(Item item,
                                                            Map<String, Object> accessLogUpdatedRecommendData,
                                                            PreferStrong preferStrong);

    /**
     * 최근 접근 기록을 업데이트하는 메소드.
     *
     * @param before 이전 개인화 기록을 담은 map.
     * @param item   최근 접근한 item.
     * @return 새로 생성된 개인화 기록. 입력의 복제본에 최근 기록이 업데이트 되어 반환된다.
     */
    protected Map<String, Object> updateRecentAccessInfo(final Map<String, Object> before, final Item item) {
        Set<Map<String, Object>> tmp = new HashSet<>(
                (List<Map<String, Object>>) before.getOrDefault("recent_items", new ArrayList<>()));
        Set<ItemData> recentItems = tmp.stream()
                .map(map -> new ItemData((int) map.get("item_id"), (String) map.get("item_name"),
                        LocalDateTime.parse(((String) map.get("access_time")).replace(" ", "T"))))
                .collect(Collectors.toSet());
        ItemData newData = new ItemData(item.getId(), item.getName(), LocalDateTime.now());
        recentItems.remove(newData);
        recentItems.add(newData);
        recentItems = recentItems.stream()
                .sorted(Comparator.comparing(ItemData::getAccessTime).reversed())
                .limit(5)
                .collect(Collectors.toSet());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Map<String, Object> result;
        try {
            result = mapper.readValue(mapper.writeValueAsString(before), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            result = new HashMap<>();
        }
        result.put("recent_items", recentItems);
        return result;
    }

    public enum PreferStrong {
        STRONG, WEEK
    }
}
