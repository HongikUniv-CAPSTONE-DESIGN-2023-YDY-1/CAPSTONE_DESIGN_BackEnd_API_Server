package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request;

import lombok.Getter;

import java.util.List;

/**
 *
 */
@Getter
public class CreateMultipleItemsRequestDTO {
    private List<CreateItemRequestDTO> itemList;
}
