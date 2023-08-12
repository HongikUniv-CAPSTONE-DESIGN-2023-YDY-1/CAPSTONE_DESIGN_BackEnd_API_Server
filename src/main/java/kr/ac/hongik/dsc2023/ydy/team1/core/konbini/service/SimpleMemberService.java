package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.JoinResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.LoginResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.PasswordChangeResponse;
import org.springframework.stereotype.Service;

@Service
public class SimpleMemberService implements MemberService {
    @Override
    public JoinResponse join(JoinRequest joinRequest) {
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public PasswordChangeResponse changePassword(PasswordChangeRequest passwordChangeRequest) {
        return null;
    }
}
