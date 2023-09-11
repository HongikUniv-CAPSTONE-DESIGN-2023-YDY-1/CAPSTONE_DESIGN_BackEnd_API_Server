package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Builder
@Getter
public class PreItemSearchResponse {
    private String name;
    private KonbiniBrand brand;
    private KonbiniPromotion promotion;
    private int pricePerUnit;
    private LocalDate startDate;
    private LocalDate endDate;
}
