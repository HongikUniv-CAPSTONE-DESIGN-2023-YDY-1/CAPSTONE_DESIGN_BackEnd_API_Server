package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.Response;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.JoinResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.LoginResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.PasswordChangeResponse;

import java.util.List;

public interface MemberService {
    JoinResponse join(JoinRequest joinRequest);
    LoginResponse login(LoginRequest loginRequest);
    PasswordChangeResponse changePassword(PasswordChangeRequest passwordChangeRequest);

    Response<List<KonbiniSearchItem>> getPersonalizeRecommendList(int memberID);
}
