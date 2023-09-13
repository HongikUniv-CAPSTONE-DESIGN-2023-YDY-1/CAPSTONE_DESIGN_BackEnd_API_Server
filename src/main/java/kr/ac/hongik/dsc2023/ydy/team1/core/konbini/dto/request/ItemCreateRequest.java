package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import lombok.*;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ItemCreateRequest {
    private String name;
    private String imgName;
    private String brand;
    private String promotion;
    private int pricePerUnit;
    private LocalDate startDate;
    private LocalDate endDate;
    private long preId;
}
