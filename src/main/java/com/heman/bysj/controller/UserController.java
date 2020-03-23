package com.heman.bysj.controller;

import com.heman.bysj.annotation.UserLoginToken;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.jooqService.StudentDao;
import com.heman.bysj.service.StudentService;
import com.heman.bysj.service.TokenService;
import com.heman.bysj.service.UserService;
import com.heman.bysj.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.web.bind.annotation.*;
import com.heman.bysj.jooq.tables.pojos.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private StudentService studentService;

    /**
     * 登录方法
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/login")
    public String login(@RequestBody Student user)  {
        System.out.println(user.getUsername()+" "+user.getPassword());

        Student userForBase=studentService.getStudentByUsername(user.getUsername(), user.getPassword());

        System.out.println("------------------------------"+userForBase);
        if(userForBase==null){
            String msg = "用户名或者密码错误";
            return msg;
        }
        String token = tokenService.getToken(userForBase);
        System.out.println(token);
        return token;
    }

    /*测试token  不登录没有token*/
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }



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
    @RequestMapping(value="/api/user/delete/{sid}")
    @ResponseBody
    public void delete(@PathVariable("sid")int id){
        String param = "student";
        if(param.equals("student")){
            userService.deleteStudent(id);
        }else if(param.equals("teacher")){
            userService.deleteTeacher(id);
        }else{
            log.info("注册用户参数错误");
            //返回错误代码
        }
    }
    @RequestMapping(value="/user/selectById/{sid}")
    @ResponseBody
    public void select(@PathVariable("sid") int id){
        String param = "student";

        if(param.equals("student")){
            userService.selectStudentById(id);
        }else if(param.equals("teacher")){
          userService.selectTeacherById(id);
             userService.selectStudentById(id);
        }else{
            log.info("注册用户参数错误");
            //返回错误代码
        }
    }
    @RequestMapping(value="/student/selectById/{sid}")
    @ResponseBody
    public Student studentSelect(@PathVariable("sid") int id){
        return userService.selectStudentById(id);

    }
    @RequestMapping(value="/student/studentDelete/{sid}")
    @ResponseBody
    public String studentDelete(@PathVariable("sid")int id){
        System.out.println("进入学生信息删除方法");
        if(userService.deleteStudent(id)>0){
            return "success";
        } else
            return "fail";
    }
    @RequestMapping(value="/student/update")
    @ResponseBody
    public String studentUpdate(@RequestBody Student student){
        System.out.println("进入学生信息修改方法");
        StudentRecord studentRecord = new StudentRecord();
        studentRecord.from(student);
      int result = studentDao.updateStudent(studentRecord);
      if(result>0){
          return "success";
      }else
          return "fail";

    }
    @RequestMapping(value="/test/{page}/{size}")
    @ResponseBody
    public Page selectTest(@PathVariable("page") int pageNum,@PathVariable("size") int limit){
           System.out.println("进入selectList方法");
         List<Student>students = new ArrayList<Student>();
        int offest;
        if(pageNum>0){
            offest =(pageNum-1)*limit;
        }else{
            offest=0;
        }
        List<StudentRecord> studentRecords = studentDao.listTest( limit, offest);
        for (StudentRecord s:studentRecords) {
            students.add(s.into(Student.class));
        }
        Page page = new Page();
        page.setData(students);
        page.setSize(limit);
        page.setTotalElement(studentDao.countStudent());
        return page;
    }
    @RequestMapping(value="/insert")
    @ResponseBody
    public String insertIntoStudent(@RequestBody Student student){
        StudentRecord studentRecord = new StudentRecord();
        studentRecord.from(student);
        int result = studentDao.insertStudent(studentRecord);
        if(result>0){
            return "success";
        }else
            return "fail";

    }

}
