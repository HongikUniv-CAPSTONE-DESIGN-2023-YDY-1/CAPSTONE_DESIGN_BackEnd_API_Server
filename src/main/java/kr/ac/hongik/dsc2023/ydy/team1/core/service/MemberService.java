package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.JoinResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.KonbiniSearchItems;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.LoginResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.PasswordChangeResponse;

public interface MemberService {
    JoinResponse join(JoinRequest joinRequest);

    LoginResponse login(LoginRequest loginRequest);

    PasswordChangeResponse changePassword(PasswordChangeRequest passwordChangeRequest);

    KonbiniSearchItems getPersonalizeRecommendList(int memberID);
}
