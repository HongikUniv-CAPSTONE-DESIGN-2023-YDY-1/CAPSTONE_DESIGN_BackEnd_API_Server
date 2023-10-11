package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberProfileRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class RecentAccessBasedPersonalizeService extends AbstractPersonalizeService {
    public RecentAccessBasedPersonalizeService(MemberRepository memberRepository, KonbiniPromotionInfoRepository promotionInfoRepository, MemberProfileRepository memberProfileRepository) {
        super(memberRepository, promotionInfoRepository, memberProfileRepository);
    }

    /**
     * 최근 접근 기록 기반 추천에서는 따로 추천 관련 정보를 저장하지 않는다. 이미 최근 접근 목록에 정보가 담겨있기 때문
     * @param item 선호하는 상품.
     * @param accessLogUpdatedRecommendData 엑세스 기록이 업데이트된 map.
     * @param preferStrong 선호 강도.
     * @return
     */
    @Override
    protected Map<String, Object> updatePreferInfo(Item item, Map<String, Object> accessLogUpdatedRecommendData, PreferStrong preferStrong) {
        return accessLogUpdatedRecommendData;
    }
}
