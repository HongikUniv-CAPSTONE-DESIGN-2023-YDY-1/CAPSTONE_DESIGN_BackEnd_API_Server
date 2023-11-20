package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import java.util.Map;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryBasedPersonalizeService extends AbstractPersonalizeService {
    public CategoryBasedPersonalizeService(MemberRepository memberRepository,
                                           KonbiniPromotionInfoRepository promotionInfoRepository,
                                           MemberProfileRepository memberProfileRepository) {
        super(memberRepository, promotionInfoRepository, memberProfileRepository);
    }

    @Override
    protected Map<String, Object> updatePreferInfo(Item item, Map<String, Object> accessLogUpdatedRecommendData,
                                                   PreferStrong preferStrong) {
        String categoryString = item.getCategory().name();
        int level = (int) accessLogUpdatedRecommendData.getOrDefault(categoryString, 0);
        if (preferStrong == PreferStrong.WEEK) {
            accessLogUpdatedRecommendData.put(categoryString, level + 1);
        }
        if (preferStrong == PreferStrong.STRONG) {
            accessLogUpdatedRecommendData.put(categoryString, level + 3);
        }
        return accessLogUpdatedRecommendData;
    }
}
