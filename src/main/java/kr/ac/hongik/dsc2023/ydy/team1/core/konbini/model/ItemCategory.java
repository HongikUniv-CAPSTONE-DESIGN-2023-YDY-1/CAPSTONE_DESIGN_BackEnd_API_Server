package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model;

import java.util.Arrays;

public enum ItemCategory {
    BEVERAGE("음료"),
    SNACK("과자류"),
    FOOD("식품"),
    ICE_CREAM("아이스크림"),
    HOUSEHOLD("생활용품");

    private final String label;

    ItemCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static ItemCategory fromValue(String value) {
        return Arrays.stream(values())
                .filter(itemCategory -> itemCategory.label.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ItemCategory: " + value));
    }
}

