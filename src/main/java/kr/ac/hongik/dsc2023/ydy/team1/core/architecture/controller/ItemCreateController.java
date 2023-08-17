package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ItemCreateResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Item 생성 API를 정의한 Controller 인터페이스
 */
public interface ItemCreateController<REQ extends ItemCreateRequest> {
    public ResponseEntity<Response<ItemCreateResponse<REQ>>> create(List<REQ> list, List<MultipartFile> multipartFiles);
}