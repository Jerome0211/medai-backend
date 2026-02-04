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
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 先全部放行（MVP阶段），后面再收紧做 JWT
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                )
                // H2 console 需要
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                // 关闭默认登录页/Basic
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
