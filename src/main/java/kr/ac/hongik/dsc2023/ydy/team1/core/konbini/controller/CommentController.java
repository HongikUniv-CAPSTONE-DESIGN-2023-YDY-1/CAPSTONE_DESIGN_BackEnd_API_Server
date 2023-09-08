package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.comment.CommentCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.comment.CommentUpdateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.comment.CommentResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.CommentService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.MemberService;
import kr.ac.hongik.dsc2023.ydy.team1.core.util.JWTMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final MemberService memberService;
    private static final int SIZE_PER_PAGE = 10;

    @PostMapping("")
    public ResponseEntity<Response<CommentResponse>> create(@RequestBody CommentCreateRequest request,
                                                            @RequestHeader(name = "Authorization") String accessToken){
        accessToken = accessToken.replace("Bearer ","");
        int userID = JWTMaker.getUserID(accessToken);
        CommentResponse data = commentService.create(request, userID);
        Response<CommentResponse> response = KonbiniResponse.<CommentResponse>builder()
                .data(data)
                .message("리뷰 작성 성공")
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping("")
    public ResponseEntity<Response<CommentResponse>> update(@RequestBody CommentUpdateRequest request,
                                                            @RequestHeader(name = "Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        int userID = JWTMaker.getUserID(accessToken);
        CommentResponse data = commentService.update(request, userID);
        Response<CommentResponse> response = KonbiniResponse.<CommentResponse>builder()
                .data(data)
                .message("리뷰 수정 성공")
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/{itemID:[0-9]+}/{page:[0-9]+}")
    public ResponseEntity<Response<Page<CommentResponse>>> getByItemID(@PathVariable long itemID,
                                                                     @PathVariable int page,
                                                                     @RequestHeader(name = "Authorization") String accessToken){
        accessToken = accessToken.replace("Bearer ", "");
        int userID = JWTMaker.getUserID(accessToken);
        var data = commentService.readAllByItemID(itemID,page-1, SIZE_PER_PAGE);
        var response = KonbiniResponse.<Page<CommentResponse>>builder()
                .data(data)
                .message("리뷰 조회 성공")
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list/{page:[0-9]+}")
    public ResponseEntity<Response<Page<CommentResponse>>> getByUserID(@PathVariable int page,
                                                                     @RequestHeader(name = "Authorization") String accessToken){
        accessToken = accessToken.replace("Bearer ", "");
        int userID = JWTMaker.getUserID(accessToken);
        var data = commentService.readAllByUserID(userID,page-1, SIZE_PER_PAGE);
        var response = KonbiniResponse.<Page<CommentResponse>>builder()
                .data(data)
                .message("리뷰 조회 성공")
                .build();
        return ResponseEntity.ok(response);
    }
}
