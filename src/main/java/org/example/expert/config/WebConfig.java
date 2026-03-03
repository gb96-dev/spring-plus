package org.example.expert.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    // 이제 시큐리티가 인증을 관리하므로 수동 리졸버 등록은 필요 없습니다.
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // resolvers.add(new AuthUserArgumentResolver()); // ← 주석 처리 또는 삭제
    }
}