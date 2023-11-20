package kr.ac.hongik.dsc2023.ydy.team1.core.dto.request;

import kr.ac.hongik.dsc2023.ydy.team1.core.entity.Member;
import kr.ac.hongik.dsc2023.ydy.team1.core.util.SHA256;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class JoinRequest {
    private String email;
    private String password;

    public static JoinRequest getInstance() {
        return new JoinRequest("test@test.com", "1234");
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(SHA256.hash(password))
                .build();
    }
}
