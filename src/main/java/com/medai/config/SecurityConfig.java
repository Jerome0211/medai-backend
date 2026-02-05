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

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ✅ 关键：让 Spring Security 启用 CORS，并读取你 CorsConfig 的规则
                .cors(Customizer.withDefaults())

                // ✅ API 常规：先关 csrf
                .csrf(csrf -> csrf.disable())

                // H2 console 需要
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                .authorizeHttpRequests(auth -> auth
                        // ✅ 关键：放行预检 OPTIONS（不然浏览器会先 OPTIONS 再 POST）
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers("/h2/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                )

                // ✅ 你要“关闭 basic”，就不要调用 httpBasic(withDefaults)
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }
}
