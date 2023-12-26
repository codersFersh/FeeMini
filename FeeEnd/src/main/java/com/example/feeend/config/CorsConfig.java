package com.example.feeend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration //标识配置类
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 使用mybatispuls
     * 方法参数注入： 直接将依赖作为方法参数，Spring Boot将根据类型进行自动注入。
     * 这种方式在方法级别进行注入。
     */

    // 注入TokenInterceptor
    //赋值成员变量
    private final TokenInterceptor tokenInterceptor;

    public CorsConfig(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // CORS 配置
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "token");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器，排除登录请求
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login");
    }
}
