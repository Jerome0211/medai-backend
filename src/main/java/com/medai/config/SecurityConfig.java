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
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. 开启 CORS 并引用下方的配置
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // 2. 禁用 CSRF（API 服务通常不需要）
                .csrf(csrf -> csrf.disable())
                // 3. 核心：放行策略
                .authorizeHttpRequests(auth -> auth
                        // 允许所有浏览器的“预检” OPTIONS 请求
                        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                        // 暂时放行所有 API 请求以供测试
                        .anyRequest().permitAll()
                )
                // 4. 禁用默认的登录表单和 HTTP Basic 认证，避免弹出登录框
                .formLogin(form -> form.disable())
                .httpBasic(hb -> hb.disable())
                // 5. 允许 H2 控制台等使用 Frame
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 允许的来源：务必包含带 www 和不带 www 的域名，以及 Vercel 的预览域名
        configuration.setAllowedOrigins(Arrays.asList(
                "https://vituslab.com",
                "https://www.vituslab.com",
                "https://medai-frontend-phi.vercel.app"
        ));

        // 允许的方法
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // 允许的 Header
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));

        // 允许发送 Cookie (如果后续需要登录功能)
        configuration.setAllowCredentials(true);

        // 预检请求的有效期（秒），设为 1 小时，减少 OPTIONS 请求次数
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}