package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordChangeRequest {
    private String email;
    private String password;
    private String newPassword;

    public static PasswordChangeRequest getInstance() {
        return new PasswordChangeRequest("test@test.com", "1234", "12345");
    }
}
