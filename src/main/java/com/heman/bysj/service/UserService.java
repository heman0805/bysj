package com.heman.bysj.service;

import com.heman.bysj.entity.UserStudent;
import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;

import java.util.Map;

public interface UserService {
    Student selectStudentById(int id);
    Student getStudentByUsername(String name,String password);

    TeacherRecord selectTeacherById(int id);
    Teacher getTeacherByUsername(String name, String password);
    Teacher getTeacherByUsernameAndStatus(String name, String password,int status);
    String userRegist(Map user);
    String changePassword(Map form,Map user);

    void cancel(int id);
    //UserStudent selectStudentById(int id);
}
