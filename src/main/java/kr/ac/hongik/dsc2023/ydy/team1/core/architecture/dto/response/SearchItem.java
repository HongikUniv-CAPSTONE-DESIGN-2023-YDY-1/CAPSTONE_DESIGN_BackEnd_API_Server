package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 검색 결과를 표현하는 추상 클래스
 */
@SuperBuilder
@Getter
@NoArgsConstructor
public abstract class SearchItem {
    protected String name;
    protected String imgUrl;
}
