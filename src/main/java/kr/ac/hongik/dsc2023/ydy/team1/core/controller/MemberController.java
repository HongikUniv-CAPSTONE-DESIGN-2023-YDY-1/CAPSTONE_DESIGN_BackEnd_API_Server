package kr.ac.hongik.dsc2023.ydy.team1.core.controller;

import java.util.List;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.JoinResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.KonbiniSearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.LoginResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.PasswordChangeResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.service.MemberService;
import kr.ac.hongik.dsc2023.ydy.team1.core.util.JWTMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<Response<JoinResponse>> join(@RequestBody JoinRequest joinRequest) {
        JoinResponse data = memberService.join(joinRequest);
        Response<JoinResponse> response = Response.<JoinResponse>builder()
                .data(data)
                .message("회원 가입 성공")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse data = memberService.login(loginRequest);
        Response<LoginResponse> response = Response.<LoginResponse>builder()
                .data(data)
                .message("로그인 성공")
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/password")
    public ResponseEntity<Response<PasswordChangeResponse>> changePassword(
            @RequestBody PasswordChangeRequest passwordChangeRequest) {
        PasswordChangeResponse data = memberService.changePassword(passwordChangeRequest);
        Response<PasswordChangeResponse> response = Response.<PasswordChangeResponse>builder()
                .data(data)
                .message("비밀번호 변경 성공")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/promotions/recommend")
    public ResponseEntity<Response<List<KonbiniSearchItem>>> getPersonalizeRecommendList(
            @RequestHeader(name = "Authorization") String accessToken) {
        accessToken = accessToken.replace("Bearer ", "");
        int userID = JWTMaker.getUserID(accessToken);
        return ResponseEntity.ok(memberService.getPersonalizeRecommendList(userID));
    }
}
