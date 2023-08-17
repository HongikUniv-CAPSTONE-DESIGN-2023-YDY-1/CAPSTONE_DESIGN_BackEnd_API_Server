package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.controller.ItemCreateController;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemsCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ItemCreateResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service.ItemCreateService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/konbini/items")
public class KonbiniItemCreateController<REQ extends ItemCreateRequest> implements ItemCreateController<REQ> {
    private final ItemCreateService<REQ, ItemsCreateRequest<REQ>> itemCreateService;
    @PostMapping("")
    @Override
    public ResponseEntity<Response<ItemCreateResponse<REQ>>> create(@RequestPart List<REQ> list, @RequestPart List<MultipartFile> multipartFiles){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setPicture(multipartFiles.get(i));
        }
        ItemsCreateRequest<REQ> requestDTO = ItemsCreateRequest.<REQ>builder().itemList(list).build();
        ItemCreateResponse<REQ> result = itemCreateService.create(requestDTO);
        KonbiniResponse<ItemCreateResponse<REQ>> wrapper = KonbiniResponse.<ItemCreateResponse<REQ>>builder()
                .data(result)
                .message("이 요청들은 실패했어요")
                .build();
        return ResponseEntity.ok(wrapper);
    }
}
