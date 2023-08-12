package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PasswordChangeRequest {
    private String email;
    private String password;
    private String newPassword;
}
