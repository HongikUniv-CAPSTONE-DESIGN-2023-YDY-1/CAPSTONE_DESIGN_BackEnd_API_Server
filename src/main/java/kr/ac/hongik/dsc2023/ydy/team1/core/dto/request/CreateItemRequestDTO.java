package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request;

import lombok.Getter;

/**
 * 상품 생성 요청을 표현하는 클래스
 */

@Getter
class CreateItemRequestDTO {
    private String name;
    private String image;
    private int price;
    private Brand brand;
    private Promotion promotion;
}
