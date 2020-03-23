package com.heman.bysj.service;

import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.records.StudentRecord;

import java.util.List;

public interface StudentService {

     List<StudentRecord> listTest(int limit,int offest);
     Student getStudentByUsername(String username,String password);
     StudentRecord selectById(int id);
}
