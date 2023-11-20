package kr.ac.hongik.dsc2023.ydy.team1.core.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.PromotionInfo;
import lombok.Getter;

@Getter
public class KonbiniSearchItems {
    private final List<KonbiniSearchItem> searchItems;

    public KonbiniSearchItems(List<PromotionInfo> promotionInfos) {
        this.searchItems = promotionInfos.stream()
                .map(KonbiniSearchItem::new)
                .collect(Collectors.toList());
    }
}
