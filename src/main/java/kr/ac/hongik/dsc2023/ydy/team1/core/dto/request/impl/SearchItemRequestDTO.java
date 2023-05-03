package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.impl;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 클라이언트의 Item 검색 요청을 표현하는 추상 클래스
 */
@SuperBuilder
@ToString
public abstract class SearchItemRequestDTO {
    protected String name;
    protected SearchStrength strength;
}
