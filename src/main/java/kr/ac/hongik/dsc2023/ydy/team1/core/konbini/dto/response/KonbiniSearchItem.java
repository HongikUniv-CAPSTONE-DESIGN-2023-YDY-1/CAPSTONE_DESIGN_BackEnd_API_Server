package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItem;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class KonbiniSearchItem extends SearchItem {
   private KonbiniBrand brand;
   private KonbiniPromotion promotion;
   private int pricePerUnit; // 개당 가격
   private int pricePerGroup; // 할인 단위당 가격
}
