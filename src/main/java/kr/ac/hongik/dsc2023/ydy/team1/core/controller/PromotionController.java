package kr.ac.hongik.dsc2023.ydy.team1.core.controller;

import java.util.List;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.ItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @PostMapping
    public ResponseEntity<Response<Boolean>> createAll(@RequestBody List<ItemCreateRequest> requests) {
        promotionService.createAll(requests);
        Response<Boolean> response = Response.<Boolean>builder()
                .data(true)
                .message("프로모션 생성 성공")
                .build();
        return ResponseEntity.ok(response);
    }
}
