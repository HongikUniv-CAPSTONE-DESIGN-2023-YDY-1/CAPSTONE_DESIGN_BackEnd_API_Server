package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemsCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ItemCreateResponseDTO;

/**
 * 상품 생성 기능을 정의하는 Service 인터페이스
 */
public interface ItemCreateService<E extends ItemCreateRequestDTO,T extends ItemsCreateRequestDTO<E>> {
    /**
     * 상품을 생성한다.
     * @param requestDTO 상품 생성 요청 DTO
     * @return 상품 생성 응답 DTO
     */
    ItemCreateResponseDTO<E> create(T requestDTO);
}
