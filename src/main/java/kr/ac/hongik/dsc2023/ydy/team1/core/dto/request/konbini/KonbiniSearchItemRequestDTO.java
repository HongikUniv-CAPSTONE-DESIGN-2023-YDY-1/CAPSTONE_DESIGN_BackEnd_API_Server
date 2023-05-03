package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.konbini;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.port.SearchItemRequestDTO;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class KonbiniSearchItemRequestDTO extends SearchItemRequestDTO {
}