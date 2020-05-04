package com.heman.bysj.controller;

import com.heman.bysj.entity.User;
import com.heman.bysj.entity.UserStudent;
import com.heman.bysj.enums.UserRole;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.jooqService.StudentDao;
import com.heman.bysj.service.TokenService;
import com.heman.bysj.service.UserService;
import com.heman.bysj.utils.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.heman.bysj.jooq.tables.pojos.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;


    /**
     * 用户登录方法
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody User user)  {
        Map<String,Object> map = new HashMap<>();
        String msg = "";
        System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getRole());
        if(user.getRole().equals(UserRole.STUDENT)) {
            Student student = userService.getStudentByUsername(user.getUsername(),user.getPassword());
            if(student==null){
                 msg= "用户名或者密码错误";
                 map.put("msg",msg);
                return map;
            }
            String token = tokenService.getToken(student);
            System.out.println(token);
            log.info("学生登陆成功：登录用户:{}",student.getName());
            map.put("msg",msg);
            map.put("token",token);
            map.put("user",student);
            return map;
        }
        else if(user.getRole().equals(UserRole.TEACHER)){
            Teacher teacher = userService.getTeacherByUsername(user.getUsername(),user.getPassword());
            if(teacher==null||!teacher.getRole().equals(UserRole.TEACHER)){
                msg = "用户名或者密码错误";
                map.put("msg",msg);
                return map;
            }
            String token = tokenService.getToken(teacher);
            System.out.println(token);
            log.info("教师登录成功:{}",teacher.getName());
            map.put("msg",msg);
            map.put("token",token);
            map.put("user",teacher);
            return map;
        }else{
            msg = "角色不能为空";
            map.put("msg",msg);
            return map;
        }

    }
    /**
     * 审批人员登录方法
     * @param user
     * @return
     */
    @RequestMapping("/adminLogin")
    public Map<String,Object> adminLogin(@RequestBody User user)  {
        Map<String,Object> map = new HashMap<>();
        String msg = "";
        System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getRole());
        Teacher teacher = userService.getTeacherByUsername(user.getUsername(),user.getPassword());
            if(teacher==null){
                msg = "用户名或者密码错误";
                map.put("msg",msg);
                return map;
            }
            String token = tokenService.getToken(teacher);
            System.out.println(token);
            log.info("审批人员登录成功:{}",teacher.getName());
            map.put("msg",msg);
            map.put("token",token);
            map.put("user",teacher);
            return map;

    }

    /**
     * 用户注册方法
     * @return
     */
    @RequestMapping("/register")
    public String register(@RequestBody Map<String, Map> params){

        Map user = params.get("user");
        String msg = userService.userRegist(user);
        return msg;

    }
    /**
     * 用户修改密码
     */
    @RequestMapping("/password")
    public String changePassword(@RequestBody Map<String, Map> params){
        String msg = "";
        Map form = params.get("form");
        Map user = params.get("user");

        msg = userService.changePassword(form,user);

        return msg;
    }
    /**
     * 学生查询个人信息
     */
    @RequestMapping("/selectStudentById/{id}")
    public UserStudent selectStudentById(@PathVariable("id") int id){
        //查询学生信息
        Student student = userService.selectStudentById(id);
        //查询辅导员信息
        TeacherRecord teacherRecord = userService.selectTeacherById(student.getTid());
        //类型转换
        UserStudent userStudent = Convert.ToUserStudent(student,teacherRecord.getName());
        return userStudent;
    }
    /**
     * 教师查询个人信息
     */
    @RequestMapping("/selectTeacherById/{id}")
    public Teacher selectTeacherById(@PathVariable("id") int id){
        //查询教师信息
        TeacherRecord teacherRecord = userService.selectTeacherById(id);
        return teacherRecord.into(Teacher.class);
    }
}
