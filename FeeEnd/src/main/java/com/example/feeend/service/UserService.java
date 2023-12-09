package com.example.feeend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.feeend.domain.User;
import com.example.feeend.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
