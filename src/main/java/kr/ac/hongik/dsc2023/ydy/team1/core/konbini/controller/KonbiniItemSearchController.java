package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.controller.ItemSearchController;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service.ItemSearchService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniSearchItemRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItem;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/konbini/items")
public class KonbiniItemSearchController<RES extends KonbiniSearchItem, REQ extends KonbiniSearchItemRequest> implements ItemSearchController<RES, REQ> {
    private final ItemSearchService<RES,REQ> itemSearchService;

    @GetMapping("")
    @Override
    public ResponseEntity<Response<SearchItemResponse<RES>>> search(REQ requestDTO) {
        System.out.println(requestDTO);
        var result = itemSearchService.search(requestDTO);
        KonbiniResponse<SearchItemResponse<RES>> wrapper = KonbiniResponse.<SearchItemResponse<RES>>builder()
                .data(result)
                .message("검색에 성공했습니다")
                .build();
        return ResponseEntity.ok(wrapper);
    }

    @PostMapping("/image")
    @Override
    public ResponseEntity<Response<SearchItemResponse<RES>>> searchByImage(@RequestPart MultipartFile img) {
        var result = itemSearchService.searchByImage(img);
        KonbiniResponse<SearchItemResponse<RES>> wrapper = KonbiniResponse.<SearchItemResponse<RES>>builder()
                .data(result)
                .message("검색에 성공했습니다")
                .build();
        return ResponseEntity.ok(wrapper);
    }
}
