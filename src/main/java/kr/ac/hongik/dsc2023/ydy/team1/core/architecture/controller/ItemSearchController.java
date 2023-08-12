package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchItemRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Item 검색 API 를 정의하는 Controller 인터페이성
 */
public interface ItemSearchController<RES extends SearchItem,REQ extends SearchItemRequest> {
    ResponseEntity<Response<SearchItemResponse<RES>>> search(REQ requestDTO);
    ResponseEntity<Response<SearchItemResponse<RES>>> searchByImage(MultipartFile img);
}
