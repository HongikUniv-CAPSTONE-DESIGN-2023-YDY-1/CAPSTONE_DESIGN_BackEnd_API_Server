package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
