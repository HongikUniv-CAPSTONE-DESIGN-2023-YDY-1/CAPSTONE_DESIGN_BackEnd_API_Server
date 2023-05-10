package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchItemRequestDTO;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@SuperBuilder
public class KonbiniSearchItemRequestDTO extends SearchItemRequestDTO {
}