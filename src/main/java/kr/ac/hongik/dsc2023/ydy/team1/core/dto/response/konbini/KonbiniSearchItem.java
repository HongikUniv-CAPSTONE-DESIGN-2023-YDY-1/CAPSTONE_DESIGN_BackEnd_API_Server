package kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.konbini;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.konbini.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.konbini.KonbiniPromotion;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.port.SearchItem;
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
