package com.example.feeend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.feeend.domain.Student;
import com.example.feeend.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {

    /**
     * 获取所有的学生
     * @return
     */
    public List<Student> StudentAll(){
        return baseMapper.StudentAll();
    }
}
