package com.medai.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//「交通规则 🚦」
//决定：请求怎么进来、怎么出去
//它一般干嘛？
//CORS（前端能不能访问）
//JSON 序列化规则
//时间格式
//跨域、编码、路径规则
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 所有路径
                .allowedOrigins(
                        // 本地开发
                        "http://localhost:3000",
                        "http://localhost:3001",

                        // 生产环境
                        "https://vituslab.com",
                        "https://www.vituslab.com",

                        // 如果 API 是单独域名（推荐）
                        "https://api.vituslab.com"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);  // 预检请求缓存 1 小时
    }
}


