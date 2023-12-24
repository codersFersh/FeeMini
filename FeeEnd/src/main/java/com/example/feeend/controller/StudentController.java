package com.example.feeend.controller;

import com.example.feeend.domain.Details;
import com.example.feeend.domain.Student;
import com.example.feeend.service.StudentService;
import com.example.feeend.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stu")
@CrossOrigin("http://localhost:8080")
public class StudentController {

    private final StudentService studentService;
    private final DetailsService detailsService;
    @Autowired
    public StudentController(StudentService studentService,DetailsService detailsService){
        this.studentService = studentService;
        this.detailsService = detailsService;
    }



    @GetMapping("all")
    @ResponseBody
    public List<Student>StudentAll(){
        return studentService.StudentAll();
    }


}
