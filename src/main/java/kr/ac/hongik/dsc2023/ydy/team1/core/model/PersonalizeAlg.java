package kr.ac.hongik.dsc2023.ydy.team1.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PersonalizeAlg {
    CATEGORY_BASE("카테고리 기반"),
    SUB_CATEGORY_BASE("하위 카테고리 기반"),
    RECENT_ACCESS_BASE("최근 접근 기반");

    private final String discription;
}

