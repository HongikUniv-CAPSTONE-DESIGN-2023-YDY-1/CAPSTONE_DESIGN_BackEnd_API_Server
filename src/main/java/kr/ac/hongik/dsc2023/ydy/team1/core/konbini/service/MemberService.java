package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.JoinResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.LoginResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.PasswordChangeResponse;

public interface MemberService {
    JoinResponse join(JoinRequest joinRequest);
    LoginResponse login(LoginRequest loginRequest);
    PasswordChangeResponse changePassword(PasswordChangeRequest passwordChangeRequest);
}
