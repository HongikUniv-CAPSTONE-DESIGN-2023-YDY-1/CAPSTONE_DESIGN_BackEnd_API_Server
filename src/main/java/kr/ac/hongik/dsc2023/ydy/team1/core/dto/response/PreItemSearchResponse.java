package kr.ac.hongik.dsc2023.ydy.team1.core.dto.response;

import java.time.LocalDate;
import kr.ac.hongik.dsc2023.ydy.team1.core.entity.PreItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.model.KonbiniPromotion;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PreItemSearchResponse {
    private long id;
    private String name;
    private KonbiniBrand brand;
    private KonbiniPromotion promotion;
    private int pricePerUnit;
    private LocalDate startDate;
    private LocalDate endDate;
    private String imgUrl;

    public static PreItemSearchResponse fromEntity(PreItem p) {
        return PreItemSearchResponse.builder()
                .id(p.getId())
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
