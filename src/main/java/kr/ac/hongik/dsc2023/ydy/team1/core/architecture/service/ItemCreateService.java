package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemsCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ItemCreateResponse;

/**
 * 상품 생성 기능을 정의하는 Service 인터페이스
 */
public interface ItemCreateService<E extends ItemCreateRequest,T extends ItemsCreateRequest<E>> {
    /**
     * 상품을 생성한다.
     * @param requestDTO 상품 생성 요청 DTO
     * @return 상품 생성 응답 DTO
     */
    ItemCreateResponse<E> create(T requestDTO);
}
