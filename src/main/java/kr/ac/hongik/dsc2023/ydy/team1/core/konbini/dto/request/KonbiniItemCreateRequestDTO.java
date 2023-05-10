package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
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
