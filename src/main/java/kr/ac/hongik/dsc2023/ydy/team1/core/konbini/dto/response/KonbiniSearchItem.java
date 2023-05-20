package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItem;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import static kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion.ONE_PLUS_ONE;

@SuperBuilder
@Getter
@ToString(callSuper = true)
public class KonbiniSearchItem extends SearchItem {
   private KonbiniBrand brand;
   private KonbiniPromotion promotion;
   private int pricePerUnit; // 개당 가격
   private int pricePerGroup; // 할인 단위당 가격
   public KonbiniSearchItem(PromotionInfo promotionInfo){
      super();
      brand = promotionInfo.getBrand();
      promotion = promotionInfo.getPromotion();
      pricePerUnit = promotionInfo.getPrice();
      if (promotion == ONE_PLUS_ONE) pricePerGroup = pricePerUnit;
      else pricePerGroup = 2 * pricePerUnit;
      name = promotionInfo.getItem().getName();
      imgUrl = promotionInfo.getItem().getImgUrl();
   }
}
