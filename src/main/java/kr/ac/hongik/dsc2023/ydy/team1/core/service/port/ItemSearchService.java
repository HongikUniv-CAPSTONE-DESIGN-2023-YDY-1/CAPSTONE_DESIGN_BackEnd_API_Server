package kr.ac.hongik.dsc2023.ydy.team1.core.service.port;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.SearchItemRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.SearchItemResponseDTO;

/**
 * 상품 검색기능을 정의하는 Service 인터페이스.
 */
public interface ItemSearchService {
    /**
     * 검색 요청 dto 를 이용해 상품을 검색한다.
     * @param requestDTO 상품 검색 조건
     * @return 상품 검색 결과
     */
    SearchItemResponseDTO search(SearchItemRequestDTO requestDTO);
}
