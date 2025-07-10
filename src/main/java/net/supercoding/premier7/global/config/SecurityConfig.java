package net.supercoding.premier7.global.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${spring.frontend.url}")
    private String frontendUrl;

    /**
     * 모든 요청을 허용하는 필터 체인 (테스트용)
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable) // API 서버이므로 CSRF 비활성화
                .formLogin(AbstractHttpConfigurer::disable) // 기본 로그인 폼 제거
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 모든 요청 허용
                );

        return http.build();
    }

    /**
     * CORS 설정
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(frontendUrl, "http://localhost:3000")); // 정확히 일치하는 도메인만 허용
        config.setAllowedMethods(List.of("*"));         // 모든 HTTP 메서드 허용
        config.setAllowedHeaders(List.of("*"));         // 모든 헤더 허용
        config.setAllowCredentials(true);               // 쿠키 포함 허용
        config.setExposedHeaders(List.of("AccessToken", "RefreshToken", "Set-Cookie")); // 응답 헤더 노출

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
