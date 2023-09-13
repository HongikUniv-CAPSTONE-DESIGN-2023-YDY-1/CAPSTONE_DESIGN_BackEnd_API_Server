package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PreItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
@Builder
@Getter
@ToString
public class PreItemSearchResponse {
    private String name;
    private KonbiniBrand brand;
    private KonbiniPromotion promotion;
    private int pricePerUnit;
    private LocalDate startDate;
    private LocalDate endDate;
    private String imgUrl;

    public static PreItemSearchResponse fromEntity(PreItem p){
        return PreItemSearchResponse.builder()
                .name(p.getName())
                .brand(p.getBrand())
                .endDate(p.getEndDate())
                .pricePerUnit(p.getPricePerUnit())
                .promotion(p.getPromotion())
                .startDate(p.getStartDate())
                .imgUrl(p.getImgURL())
                .build();
    }
}
