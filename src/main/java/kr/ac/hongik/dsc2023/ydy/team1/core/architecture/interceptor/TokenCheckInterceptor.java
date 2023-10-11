package kr.ac.hongik.dsc2023.ydy.team1.core.architecture.interceptor;

import kr.ac.hongik.dsc2023.ydy.team1.core.util.JWTMaker;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class TokenCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("Authorization");
        if(accessToken == null){
            throw new IllegalArgumentException("엑세스 토큰이 없습니다.");
        }
        return true;
    }
}
