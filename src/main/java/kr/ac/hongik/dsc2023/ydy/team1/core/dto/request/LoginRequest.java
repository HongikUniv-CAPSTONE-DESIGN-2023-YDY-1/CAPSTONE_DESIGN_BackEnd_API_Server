package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;

    public static LoginRequest getInstance() {
        return new LoginRequest("test@test.com", "1234");
    }
}
