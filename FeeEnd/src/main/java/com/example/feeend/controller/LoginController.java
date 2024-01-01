package com.example.feeend.controller;

import com.example.feeend.config.TokenUtil;
import com.example.feeend.domain.User;
import com.example.feeend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin("http://localhost:8080/")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            User loginUser = userService.LoginUser(user);
            String token = TokenUtil.login(loginUser.getId());

            // 返回登录用户信息和Token
            Map<String, Object> result = new HashMap<>();
            result.put("user", loginUser);
            result.put("token", token);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<?>logout(HttpServletRequest request){
//        String token = request.getHeader("Authorization");
        String token = request.getHeader("Token");
        if(token !=null){
            TokenUtil.logout(token);
        }
        return ResponseEntity.ok("注销成功");
    }
}
