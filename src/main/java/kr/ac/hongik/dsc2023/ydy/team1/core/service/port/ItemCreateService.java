package kr.ac.hongik.dsc2023.ydy.team1.core.service.port;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.CreateMultipleItemsRequestDTO;

/**
 * 상품 생성 기능을 정의하는 Service 인터페이스
 */
public interface ItemCreateService {
    /**
     * 상품을 생성한다.
     * @param requestDTO 상품 생성 요청 DTO
     */
    void create(CreateMultipleItemsRequestDTO requestDTO);
}
