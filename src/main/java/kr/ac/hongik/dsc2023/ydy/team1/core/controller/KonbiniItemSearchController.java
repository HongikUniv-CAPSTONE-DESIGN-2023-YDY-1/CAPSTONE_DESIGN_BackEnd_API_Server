package kr.ac.hongik.dsc2023.ydy.team1.core.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.KonbiniSearchItemRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.KonbiniSearchItems;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.service.KonbiniItemSearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/konbini/items")
public class KonbiniItemSearchController {
    private final KonbiniItemSearchService itemSearchService;

    @GetMapping("")
    public ResponseEntity<Response<KonbiniSearchItems>> search(
            KonbiniSearchItemRequest requestDTO) {
        System.out.println(requestDTO);
        var result = itemSearchService.search(requestDTO);
        Response<KonbiniSearchItems> wrapper = Response.<KonbiniSearchItems>builder()
                .data(result)
                .message("검색에 성공했습니다")
                .build();
        return ResponseEntity.ok(wrapper);
    }

    @PostMapping("/image")
    public ResponseEntity<Response<KonbiniSearchItems>> searchByImage(
            @RequestPart MultipartFile img) {
        var result = itemSearchService.searchByImage(img);
        Response<KonbiniSearchItems> wrapper = Response.<KonbiniSearchItems>builder()
                .data(result)
                .message("검색에 성공했습니다")
                .build();
        return ResponseEntity.ok(wrapper);
    }
}
