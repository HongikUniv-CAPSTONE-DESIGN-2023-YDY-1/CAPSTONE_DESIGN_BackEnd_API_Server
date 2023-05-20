package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.controller.ItemCreateController;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemsCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ItemCreateResponseDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ResponseWrapper;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service.ItemCreateService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniItemCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniItemCreateResponseDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniResponseWrapper;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItem;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/konbini/items")
public class KonbiniItemCreateController<REQ extends ItemCreateRequestDTO> implements ItemCreateController<REQ> {
    private final ItemCreateService<REQ,ItemsCreateRequestDTO<REQ>> itemCreateService;
    @PostMapping("")
    @Override
    public ResponseEntity<ResponseWrapper<ItemCreateResponseDTO<REQ>>> create(@RequestPart List<REQ> list, @RequestPart List<MultipartFile> multipartFiles){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setPicture(multipartFiles.get(i));
        }
        ItemsCreateRequestDTO<REQ> requestDTO = ItemsCreateRequestDTO.<REQ>builder().itemList(list).build();
        ItemCreateResponseDTO<REQ> result = itemCreateService.create(requestDTO);
        KonbiniResponseWrapper<ItemCreateResponseDTO<REQ>> wrapper = KonbiniResponseWrapper.<ItemCreateResponseDTO<REQ>>builder()
                .response(result)
                .message("이 요청들은 실패했어요")
                .build();
        return ResponseEntity.ok(wrapper);
    }
}
