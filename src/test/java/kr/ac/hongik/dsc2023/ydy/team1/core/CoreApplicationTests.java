package kr.ac.hongik.dsc2023.ydy.team1.core;


import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CoreApplicationTests {
    @Autowired
    MemberService memberService;

    @Test
    public void memberServiceTest(){
        memberService.join(JoinRequest.getInstance());
        memberService.login(LoginRequest.getInstance());
        memberService.changePassword(PasswordChangeRequest.getInstance());
        Assertions.assertThrows(RuntimeException.class,() -> {
            memberService.login(LoginRequest.getInstance());
        });
    }
}