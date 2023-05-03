package kr.ac.hongik.dsc2023.ydy.team1.core.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.impl.ItemsCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.ItemCreateResponseDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.ResponseWrapper;
import org.springframework.http.ResponseEntity;

/**
 * Item 생성 API를 정의한 Controller 인터페이스
 */
public interface ItemCreateController {
    ResponseWrapper<ResponseEntity<ItemCreateResponseDTO>> create(ItemsCreateRequestDTO requestDTO);
}