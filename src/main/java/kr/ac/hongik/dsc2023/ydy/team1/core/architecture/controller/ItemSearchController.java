package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchItemRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ResponseWrapper;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Item 검색 API 를 정의하는 Controller 인터페이성
 */
public interface ItemSearchController<RES extends SearchItem,REQ extends SearchItemRequestDTO> {
    ResponseEntity<ResponseWrapper<SearchItemResponseDTO<RES>>> search(REQ requestDTO);
    ResponseEntity<ResponseWrapper<SearchItemResponseDTO<RES>>> searchByImage(MultipartFile img);
}
