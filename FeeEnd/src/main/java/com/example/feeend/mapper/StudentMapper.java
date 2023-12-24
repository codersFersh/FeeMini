package com.example.feeend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.feeend.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper  extends BaseMapper<Student> {

    /**
     * 获取所有学生
     * @return
     */
    @Select("select * from student")
    List<Student> StudentAll();

}
