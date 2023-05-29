package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchItemRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponseDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Item 검색기능을 정의하는 Service 인터페이스.
 */
public interface ItemSearchService<E extends SearchItem,T extends SearchItemRequestDTO> {
    /**
     * 검색 요청 dto 를 이용해 상품을 검색한다.
     * @param requestDTO 상품 검색 조건
     * @return Item 검색 결과
     */
    SearchItemResponseDTO<E> search(T requestDTO);
    SearchItemResponseDTO<E> searchByImage(MultipartFile img);
}
