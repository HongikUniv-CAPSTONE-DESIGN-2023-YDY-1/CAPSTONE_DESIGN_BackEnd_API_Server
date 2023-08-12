package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Member;
import kr.ac.hongik.dsc2023.ydy.team1.core.util.SHA256;
import lombok.Getter;

@Getter
public class JoinRequest {
    private String email;
    private String password;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(SHA256.hash(password))
                .build();
    }
}
