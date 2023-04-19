package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request;

import lombok.Getter;

import java.util.List;

/**
 * 여러개의 Item을 한번에 생성하는 요청을 표현하는 추상 클래스
 */
@Getter
public abstract class ItemsCreateRequestDTO {
    private List<ItemCreateRequestDTO> itemList;
}
