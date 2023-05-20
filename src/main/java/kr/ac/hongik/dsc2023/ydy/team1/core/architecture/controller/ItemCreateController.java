package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemsCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ItemCreateResponseDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Item 생성 API를 정의한 Controller 인터페이스
 */
public interface ItemCreateController<REQ extends ItemCreateRequestDTO> {
    public ResponseEntity<ResponseWrapper<ItemCreateResponseDTO<REQ>>> create(@RequestPart List<REQ> list, @RequestPart List<MultipartFile> multipartFiles);
}