package kr.ac.hongik.dsc2023.ydy.team1.core.config;

import kr.ac.hongik.dsc2023.ydy.team1.core.interceptor.TokenCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final TokenCheckInterceptor tokenCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenCheckInterceptor)
                .addPathPatterns("/comment/**");
    }
}
