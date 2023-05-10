package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
/**
 * Item 검색 결과를 표현하는 추상 클래스
 */
public abstract class SearchItemResponseDTO {
    protected List<SearchItem> searchItems;
}
