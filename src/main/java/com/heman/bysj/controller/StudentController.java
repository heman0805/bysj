package com.heman.bysj.controller;


import com.heman.bysj.jooq.tables.records.StudentRecord;
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
    public List<StudentRecord> test(){
        int limit=10, offest = 0;
        List<StudentRecord> testList = studentService.listTest( limit, offest);
        return  testList;
    }
}
