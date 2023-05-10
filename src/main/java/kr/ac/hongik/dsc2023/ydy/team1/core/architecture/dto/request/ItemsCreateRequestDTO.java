package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * 여러개의 Item을 한번에 생성하는 요청을 표현하는 클래스
 */
@Getter
@Builder
@ToString
public class ItemsCreateRequestDTO<T extends ItemCreateRequestDTO> {
    private final List<T> itemList;
}
