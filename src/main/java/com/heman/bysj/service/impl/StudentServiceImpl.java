package com.heman.bysj.service.impl;

import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooqService.StudentDao;
import com.heman.bysj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public Student getStudentByUsername(String username, String password) {
        return studentDao.getStudnetByUsername(username,password).into(Student.class);
    }

    @Override
    public List<StudentRecord> listTest(int limit,int offest) {
        return studentDao.listTest( limit, offest);
    }

    @Override
    public StudentRecord selectById(int id) {
        return studentDao.selectById(id);
    }
}
