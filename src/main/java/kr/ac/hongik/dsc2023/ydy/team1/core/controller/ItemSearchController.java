package kr.ac.hongik.dsc2023.ydy.team1.core.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.SearchItemRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.ResponseWrapper;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.SearchItemResponseDTO;
import org.springframework.http.ResponseEntity;

/**
 * Item 검색 API 를 정의하는 Controller 인터페이성
 */
public interface ItemSearchController {
    ResponseWrapper<ResponseEntity<SearchItemResponseDTO>> search(SearchItemRequestDTO requestDTO);
}
