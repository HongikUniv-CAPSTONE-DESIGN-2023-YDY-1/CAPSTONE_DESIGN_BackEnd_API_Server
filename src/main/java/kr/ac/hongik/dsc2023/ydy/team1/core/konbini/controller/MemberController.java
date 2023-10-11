package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.*;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.MemberService;
import kr.ac.hongik.dsc2023.ydy.team1.core.util.JWTMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("")
    public ResponseEntity<Response<JoinResponse>> join(@RequestBody JoinRequest joinRequest){
        JoinResponse data = memberService.join(joinRequest);
        KonbiniResponse<JoinResponse> response = KonbiniResponse.<JoinResponse>builder()
                .data(data)
                .message("회원 가입 성공")
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
        LoginResponse data = memberService.login(loginRequest);
        KonbiniResponse<LoginResponse> response = KonbiniResponse.<LoginResponse>builder()
                .data(data)
                .message("로그인 성공")
                .build();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/password")
    public ResponseEntity<Response<PasswordChangeResponse>> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest){
        PasswordChangeResponse data = memberService.changePassword(passwordChangeRequest);
        KonbiniResponse<PasswordChangeResponse> response = KonbiniResponse.<PasswordChangeResponse>builder()
                .data(data)
                .message("비밀번호 변경 성공")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/promotions/recommend")
    public ResponseEntity<Response<List<KonbiniSearchItem>>> getPersonalizeRecommendList(@RequestHeader(name = "Authorization") String accessToken){
        accessToken = accessToken.replace("Bearer ","");
        int userID = JWTMaker.getUserID(accessToken);
        return ResponseEntity.ok(memberService.getPersonalizeRecommendList(userID));
    }
}
