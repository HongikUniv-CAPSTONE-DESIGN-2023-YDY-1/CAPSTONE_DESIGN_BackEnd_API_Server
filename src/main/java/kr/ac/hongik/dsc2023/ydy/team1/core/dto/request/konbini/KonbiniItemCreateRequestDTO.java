package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.konbini;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.impl.ItemCreateRequestDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class KonbiniItemCreateRequestDTO extends ItemCreateRequestDTO {
    private final KonbiniBrand brand;
    private final KonbiniPromotion promotion;
    private final long pricePerUnit;
}
