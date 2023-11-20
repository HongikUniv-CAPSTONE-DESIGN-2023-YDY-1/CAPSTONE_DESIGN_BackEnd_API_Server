package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response;

import static kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion.ONE_PLUS_ONE;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class KonbiniSearchItem {
    private long id;
    private String name;
    private String imgUrl;
    private KonbiniBrand brand;
    private KonbiniPromotion promotion;
    private int pricePerUnit; // 개당 가격
    private int pricePerGroup; // 할인 단위당 가격

    public KonbiniSearchItem(PromotionInfo promotionInfo) {
        super();
        brand = promotionInfo.getBrand();
        promotion = promotionInfo.getPromotion();
        pricePerUnit = promotionInfo.getPrice();
        if (promotion == ONE_PLUS_ONE) {
            pricePerGroup = pricePerUnit;
        } else {
            pricePerGroup = 2 * pricePerUnit;
        }
        id = promotionInfo.getItem().getId();
        name = promotionInfo.getItem().getName();
        imgUrl = promotionInfo.getItem().getImgUrl();
    }
}
