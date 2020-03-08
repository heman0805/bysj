package com.heman.bysj.service.impl;

import com.heman.bysj.mapper.StudentMapper;
import com.heman.bysj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heman.bysj.model.entity.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> listTest() {
        return studentMapper.listTest();
    }
}
