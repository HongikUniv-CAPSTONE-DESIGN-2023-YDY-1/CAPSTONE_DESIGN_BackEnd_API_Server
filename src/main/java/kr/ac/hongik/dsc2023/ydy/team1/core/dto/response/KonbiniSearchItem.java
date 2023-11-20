package kr.ac.hongik.dsc2023.ydy.team1.core.dto.response;

import kr.ac.hongik.dsc2023.ydy.team1.core.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.KonbiniPromotion;
import lombok.Getter;

@Getter
public class KonbiniSearchItem {
    private final long id;
    private final String name;
    private final String imgUrl;
    private final KonbiniBrand brand;
    private final KonbiniPromotion promotion;
    private final int pricePerUnit; // 개당 가격
    private final int pricePerGroup; // 할인 단위당 가격

    public KonbiniSearchItem(PromotionInfo promotionInfo) {
        super();
        brand = promotionInfo.getBrand();
        promotion = promotionInfo.getPromotion();
        pricePerUnit = promotionInfo.getPrice();
        if (promotion == KonbiniPromotion.ONE_PLUS_ONE) {
            pricePerGroup = pricePerUnit;
        } else {
            pricePerGroup = 2 * pricePerUnit;
        }
        id = promotionInfo.getItem().getId();
        name = promotionInfo.getItem().getName();
        imgUrl = promotionInfo.getItem().getImgUrl();
    }
}
