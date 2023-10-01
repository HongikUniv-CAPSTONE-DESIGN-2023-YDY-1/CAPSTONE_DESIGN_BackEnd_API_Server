package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Member;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.MemberProfile;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.ItemData;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.PersonalizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static kr.ac.hongik.dsc2023.ydy.team1.core.konbini.event.PersonalizeEvent.RecommendType.COMMENT_READ;
import static kr.ac.hongik.dsc2023.ydy.team1.core.konbini.event.PersonalizeEvent.RecommendType.COMMENT_WRITE;

@Component
@RequiredArgsConstructor
public class PersonalizeEventHandler implements PersonalizeService {
    private final MemberRepository memberRepository;
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    private final MemberProfileRepository memberProfileRepository;
    @Transactional
    @EventListener
    @Override
    public void recommend(PersonalizeEvent personalizeEvent) {
        int memberID = personalizeEvent.getMemberID();
        long promotionID = personalizeEvent.getPromotionID();
        Member member = memberRepository.findById(memberID).orElseThrow(() -> new NoSuchElementException("없는 회원입니다."));
        MemberProfile memberProfile = memberProfileRepository.findByMember_Id(memberID).orElse(new MemberProfile(member));
        PromotionInfo promotionInfo = promotionInfoRepository.findById(promotionID).orElse(null);

        boolean isInValidRecommend = promotionInfo == null;
        if (isInValidRecommend) {
            return;
        }

        Map<String, Object> recommendData = updateProfile(memberProfile.getRecommendData(), promotionInfo.getItem(), personalizeEvent.getRecommendType());
        memberProfile.update(recommendData);
        memberProfileRepository.save(memberProfile);
    }

    /**
     * 사용자 추천 정보를 업데이트 한다. 입력으로 주어진 Map은 변경되지 않는다.
     * @param before 기존의 추천 정보
     * @param item 접근한 프로모션의 아이템 데이터
     * @param recommendType 추천 정보 업데이트 타입
     * @return 업데이트된 추천 정보.
     */
    private Map<String,Object> updateProfile(final Map<String,Object> before, final Item item, PersonalizeEvent.RecommendType recommendType){
        Set<Map<String,String>> tmp = new HashSet<>((List<Map<String,String>>)before.getOrDefault("recent_items",new ArrayList<>()));
        Set<ItemData> recentItems = tmp.stream().map(map -> new ItemData(map.get("item_name"), LocalDateTime.parse(map.get("access_time").replace(" ","T"))))
                .collect(Collectors.toSet());
        ItemData newData = new ItemData(item.getName(), LocalDateTime.now());
        recentItems.remove(newData);
        recentItems.add(newData);
        recentItems = recentItems.stream()
                .sorted(Comparator.comparing(ItemData::getAccessTime).reversed())
                .limit(5)
                .collect(Collectors.toSet());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Map<String,Object> result;
        try {
            result = mapper.readValue(mapper.writeValueAsString(before), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            result = new HashMap<>();
        }
        result.put("recent_items",recentItems);
        String categoryString = item.getCategory().name();
        int level = (int) result.getOrDefault(categoryString,0);
        if (recommendType.equals(COMMENT_READ)){
            result.put(categoryString,level + 1);
        }
        if (recommendType.equals(COMMENT_WRITE)){
            result.put(categoryString,level + 3);
        }
        return result;
    }
}
