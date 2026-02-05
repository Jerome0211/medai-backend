package com.medai.config;



//「门禁保安 🚪」
//决定：谁能进系统？从哪进？哪些门现在先不开？
//放行哪些 API（比如 /api/**）
//
//要不要登录
//
//用 JWT / Basic Auth / 全部放开
//
//生产 vs 开发策略
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. 直接在这里集成 CORS 配置
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 必须放行 OPTIONS
                        .anyRequest().permitAll()
                )
                .httpBasic(hb -> hb.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }

    // 2. 显式定义这个 Bean，确保 Security 能认到它
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许你的前端域名
        configuration.setAllowedOrigins(Arrays.asList("https://vituslab.com", "https://www.vituslab.com"));
        // 允许所有方法和 Header
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
