package kr.ac.hongik.dsc2023.ydy.team1.core.model;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ItemCategory {
    BEVERAGE("음료"),
    SNACK("과자류"),
    FOOD("식품"),
    ICE_CREAM("아이스크림"),
    HOUSEHOLD("생활용품");

    private final String label;

    public static ItemCategory fromValue(String value) {
        return Arrays.stream(values())
                .filter(itemCategory -> itemCategory.label.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ItemCategory: " + value));
    }

    public String getLabel() {
        return label;
    }
}

