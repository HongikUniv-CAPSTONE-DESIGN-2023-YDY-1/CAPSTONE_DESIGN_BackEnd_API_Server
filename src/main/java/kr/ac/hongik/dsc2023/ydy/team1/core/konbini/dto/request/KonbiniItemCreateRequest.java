package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString(callSuper = true)
@Setter
@NoArgsConstructor
public class KonbiniItemCreateRequest extends ItemCreateRequest {
    private KonbiniBrand brand;
    private KonbiniPromotion promotion;
    private int pricePerUnit;

    public PromotionInfo toPromotionInfo(Item item){
        return PromotionInfo.builder()
                .price(pricePerUnit)
                .promotion(promotion)
                .brand(brand)
                .item(item)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();
    }
}
