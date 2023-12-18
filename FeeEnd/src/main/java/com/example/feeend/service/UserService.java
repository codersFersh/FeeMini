package com.example.feeend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.feeend.domain.User;
import com.example.feeend.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public User LoginUser(User user) throws Exception{
        // 通过接口获取 username 和 password
        User loginUser = baseMapper.LoginUser(user);
        if (loginUser == null) {
            // 抛出异常
            throw new Exception("用户名或密码错误");
        } else if (!loginUser.getPassword().equals(user.getPassword())) {
            // 抛出异常
            throw new Exception("用户名或密码错误");
        }
        return loginUser;
    }
}
