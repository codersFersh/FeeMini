package com.example.feeend.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取请求路径
        String requestURI = request.getRequestURI();
        // 如果请求路径是登录路径，直接放行
        if ("/user/login".equals(requestURI)) {
            return true;
        }else {
            // 获取请求中的token
            String token = request.getHeader("token");

            // 如果请求没有携带 token，或者 token 无效
            if (token == null || !TokenUtil.verifyToken(token)) {

                //清除token,以便于重新登录
                TokenUtil.logout(token);

                //返回401，让前端跳回登录页
                response.setStatus(401);

                return false; // 拦截请求，不再向下执行
            }else {
                // 如果携带了有效的 token，放行请求
                return true;
            }

        }

//        return true;
    }
}
