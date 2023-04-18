package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request;

import lombok.Getter;

/**
 *
 */

@Getter
class CreateItemRequestDTO {
    private String name;
    private String image;
    private int price;
    private Brand brand;
    private Promotion promotion;
}
