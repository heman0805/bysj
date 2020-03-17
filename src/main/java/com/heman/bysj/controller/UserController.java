package com.heman.bysj.controller;

import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/api/user/insert")
    @ResponseBody
    public void insert(){

        TeacherRecord teacherRecord = new TeacherRecord();
        teacherRecord.setTid(5);
        teacherRecord.setName("王小燕");
        teacherRecord.setUsername("wangxiaoyan");
        teacherRecord.setPassword("980805");
        teacherRecord.setCollege("计算机学院");
        teacherRecord.setProfession("软件工程");
        teacherRecord.setPosition("教师");
        String param = "teacher";

        StudentRecord studentRecord = new StudentRecord();
        if(param.equals("student")){
            userService.insertIntoStudent(studentRecord);
        }else if(param.equals("teacher")){
            userService.insertIntoTeacher(teacherRecord);
        }else{
            log.info("注册用户参数错误");
            //返回错误代码
        }
    }
    @RequestMapping(value="/api/user/update")
    @ResponseBody
    public void update(){
        String param = "student";
        TeacherRecord teacherRecord = new TeacherRecord();
        StudentRecord studentRecord = new StudentRecord();

        if(param.equals("student")){
            userService.updateStudent(studentRecord);
        }else if(param.equals("teacher")){
            userService.updateTeacher(teacherRecord);
        }else{
            log.info("注册用户参数错误");
            //返回错误代码
        }
    }
    @RequestMapping(value="/api/user/delete")
    @ResponseBody
    public void delete(){
        String param = "student";
        int id = 1;
        if(param.equals("student")){
            userService.deleteStudent(id);
        }else if(param.equals("teacher")){
            userService.deleteTeacher(id);
        }else{
            log.info("注册用户参数错误");
            //返回错误代码
        }
    }
    @RequestMapping(value="/api/user/select")
    @ResponseBody
    public void select(){
        String param = "student";
        int id = 1;
        if(param.equals("student")){
            userService.selectStudentById(id);
        }else if(param.equals("teacher")){
            userService.selectTeacherById(id);
        }else{
            log.info("注册用户参数错误");
            //返回错误代码
        }
    }
}
