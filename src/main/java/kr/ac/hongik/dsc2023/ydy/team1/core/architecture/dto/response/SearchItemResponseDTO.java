package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public abstract class SearchItemResponseDTO<T extends SearchItem> {
    protected List<T> searchItems;
}
