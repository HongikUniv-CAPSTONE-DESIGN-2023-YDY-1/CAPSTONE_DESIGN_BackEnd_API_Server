package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinResponse {
    private String accessToken;
    private String refreshToken;
}
