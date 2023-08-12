package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ResponseWrapper;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.JoinResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniResponseWrapper;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.LoginResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.PasswordChangeResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("")
    public ResponseEntity<ResponseWrapper<JoinResponse>> join(@RequestBody JoinRequest joinRequest){
        JoinResponse data = memberService.join(joinRequest);
        KonbiniResponseWrapper<JoinResponse> response = KonbiniResponseWrapper.<JoinResponse>builder()
                .response(data)
                .message("회원 가입 성공")
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
        LoginResponse data = memberService.login(loginRequest);
        KonbiniResponseWrapper<LoginResponse> response = KonbiniResponseWrapper.<LoginResponse>builder()
                .response(data)
                .message("로그인 성공")
                .build();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/password")
    public ResponseEntity<ResponseWrapper<PasswordChangeResponse>> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest){
        PasswordChangeResponse data = memberService.changePassword(passwordChangeRequest);
        KonbiniResponseWrapper<PasswordChangeResponse> response = KonbiniResponseWrapper.<PasswordChangeResponse>builder()
                .response(data)
                .message("비밀번호 변경 성공")
                .build();
        return ResponseEntity.ok(response);
    }
}
