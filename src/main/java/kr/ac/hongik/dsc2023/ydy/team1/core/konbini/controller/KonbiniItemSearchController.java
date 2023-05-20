package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.controller.ItemSearchController;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchItemRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ResponseWrapper;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.SearchItemResponseDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service.ItemSearchService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniSearchItemRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniResponseWrapper;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItem;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/konbini/items")
public class KonbiniItemSearchController<RES extends KonbiniSearchItem, REQ extends KonbiniSearchItemRequestDTO> implements ItemSearchController<RES, REQ> {
    private final ItemSearchService<RES,REQ> itemSearchService;

    @GetMapping("")
    @Override
    public ResponseEntity<ResponseWrapper<SearchItemResponseDTO<RES>>> search(REQ requestDTO) {
        System.out.println(requestDTO);
        var result = itemSearchService.search(requestDTO);
        KonbiniResponseWrapper<SearchItemResponseDTO<RES>> wrapper = KonbiniResponseWrapper.<SearchItemResponseDTO<RES>>builder()
                .response(result)
                .message("검색에 성공했습니다")
                .build();
        return ResponseEntity.ok(wrapper);
    }
}
