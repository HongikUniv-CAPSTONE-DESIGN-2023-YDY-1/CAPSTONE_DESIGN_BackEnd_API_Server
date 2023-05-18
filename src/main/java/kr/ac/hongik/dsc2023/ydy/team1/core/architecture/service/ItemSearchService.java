package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchItemRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponseDTO;

/**
 * Item 검색기능을 정의하는 Service 인터페이스.
 */
public interface ItemSearchService {
    /**
     * 검색 요청 dto 를 이용해 상품을 검색한다.
     * @param requestDTO 상품 검색 조건
     * @return Item 검색 결과
     */
    SearchItemResponseDTO search(SearchItemRequestDTO requestDTO);
}