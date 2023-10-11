package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.ItemCategory;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SubCategoryBasedPersonalizeService extends AbstractPersonalizeService {
    public SubCategoryBasedPersonalizeService(MemberRepository memberRepository, KonbiniPromotionInfoRepository promotionInfoRepository, MemberProfileRepository memberProfileRepository) {
        super(memberRepository, promotionInfoRepository, memberProfileRepository);
    }

    @Override
    protected Map<String, Object> updatePreferInfo(Item item, final Map<String, Object> accessLogUpdatedRecommendData, final PreferStrong preferStrong) {
        ItemCategory category = item.getCategory();
        Map<String, Object> subCategory = item.getSubCategory();
        Set<String> subCategoryNames = subCategory.keySet();
        subCategoryNames.stream()
                .map(s->category.name()+"-"+s)
                .forEach(category_subCategory -> updatePreferInfo(accessLogUpdatedRecommendData,category_subCategory,preferStrong));
        return accessLogUpdatedRecommendData;
    }
    private void updatePreferInfo(Map<String, Object> accessLogUpdatedRecommendData, String category, PreferStrong preferStrong){
        int level = (int) accessLogUpdatedRecommendData.getOrDefault(category,0);
        if (preferStrong == PreferStrong.WEEK){
            accessLogUpdatedRecommendData.put(category,level + 1);
        }
        if (preferStrong == PreferStrong.STRONG){
            accessLogUpdatedRecommendData.put(category,level + 3);
        }
    }
}
