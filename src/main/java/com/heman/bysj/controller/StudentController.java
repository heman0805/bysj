package com.heman.bysj.controller;


import com.heman.bysj.model.entity.Student;
import com.heman.bysj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;


    @CrossOrigin
    @RequestMapping(value="/api/test",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Student> test(){
        List<Student> testList = studentService.listTest();
        return  testList;
    }
}
