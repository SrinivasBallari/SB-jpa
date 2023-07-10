package com.srinivas.jpa.controller;

import com.srinivas.jpa.entity.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @PostMapping("/api/students")
    public void saveStudent(@RequestBody Student student){
        System.out.println(student);
    }
}
