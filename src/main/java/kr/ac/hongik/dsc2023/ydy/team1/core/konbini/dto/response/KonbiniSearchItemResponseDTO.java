package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponseDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class KonbiniSearchItemResponseDTO<T extends SearchItem> extends SearchItemResponseDTO<T> {
}
