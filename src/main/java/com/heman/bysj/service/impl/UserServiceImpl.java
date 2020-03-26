package com.heman.bysj.service.impl;

import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.jooqService.StudentDao;
import com.heman.bysj.jooqService.TeacherDao;
import com.heman.bysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public int insertIntoStudent(StudentRecord studentRecord) {
        return studentDao.insertStudent(studentRecord);
    }

    @Override
    public int updateStudent(StudentRecord studentRecord) {
        return studentDao.updateStudent(studentRecord);
    }

    @Override
    public int deleteStudent(int id) {
        return studentDao.deleteById(id);
    }

    @Override
    public Student selectStudentById(int id) {
        return studentDao.selectById(id).into(Student.class);
    }

    @Override
    public int insertIntoTeacher(TeacherRecord teacherRecord) {
        return teacherDao.insertTeacher(teacherRecord);
    }

    @Override
    public int updateTeacher(TeacherRecord teacherRecord) {
        return teacherDao.updateTeacher(teacherRecord);
    }

    @Override
    public int deleteTeacher(int id) {
        return teacherDao.deleteById(id);
    }

    @Override
    public TeacherRecord selectTeacherById(int id) {
        return teacherDao.selectById(id);
    }

    @Override
    public Student getStudentByUsername(String name, String password) {
        StudentRecord studentRecord = studentDao.getStudnetByUsername(name,password);
        if(studentRecord!=null)
            return studentRecord.into(Student.class);
        else
            return null;
        //return studentDao.getStudnetByUsername(name,password).into(Student.class);
    }

    @Override
    public Teacher getTeacherByUsername(String name, String password) {
        TeacherRecord teacherRecord = teacherDao.getTeacherByUsername(name,password);
        if(teacherRecord!=null)
            return teacherRecord.into(Teacher.class);
        else
            return null;
        //return teacherDao.getTeacherByUsername(name,password).into(Teacher.class);
    }
}
