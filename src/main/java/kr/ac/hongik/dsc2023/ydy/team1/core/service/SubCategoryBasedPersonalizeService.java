package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import java.util.Map;
import java.util.Set;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.ItemCategory;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.repository.MemberRepository;
import org.springframework.stereotype.Service;


@Service
public class SubCategoryBasedPersonalizeService extends AbstractPersonalizeService {
    public SubCategoryBasedPersonalizeService(MemberRepository memberRepository,
                                              KonbiniPromotionInfoRepository promotionInfoRepository,
                                              MemberProfileRepository memberProfileRepository) {
        super(memberRepository, promotionInfoRepository, memberProfileRepository);
    }

    @Override
    protected Map<String, Object> updatePreferInfo(Item item, final Map<String, Object> accessLogUpdatedRecommendData,
                                                   final PreferStrong preferStrong) {
        ItemCategory category = item.getCategory();
        Map<String, Object> subCategory = item.getSubCategory();
        Set<String> subCategoryNames = subCategory.keySet();
        subCategoryNames.stream()
                .map(s -> category.name() + "-" + s)
                .forEach(category_subCategory -> updatePreferInfo(accessLogUpdatedRecommendData, category_subCategory,
                        preferStrong));
        return accessLogUpdatedRecommendData;
    }

    private void updatePreferInfo(Map<String, Object> accessLogUpdatedRecommendData, String category,
                                  PreferStrong preferStrong) {
        int level = (int) accessLogUpdatedRecommendData.getOrDefault(category, 0);
        if (preferStrong == PreferStrong.WEEK) {
            accessLogUpdatedRecommendData.put(category, level + 1);
        }
        if (preferStrong == PreferStrong.STRONG) {
            accessLogUpdatedRecommendData.put(category, level + 3);
        }
    }
}
