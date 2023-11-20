package kr.ac.hongik.dsc2023.ydy.team1.core.service;

import java.util.List;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.JoinResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.KonbiniSearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.LoginResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.PasswordChangeResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.response.Response;

public interface MemberService {
    JoinResponse join(JoinRequest joinRequest);

    LoginResponse login(LoginRequest loginRequest);

    PasswordChangeResponse changePassword(PasswordChangeRequest passwordChangeRequest);

    Response<List<KonbiniSearchItem>> getPersonalizeRecommendList(int memberID);
}
