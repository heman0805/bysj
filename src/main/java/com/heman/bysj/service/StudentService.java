package com.heman.bysj.service;

import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.model.entity.Student;

import java.util.List;

public interface StudentService {

     List<StudentRecord> listTest();
     StudentRecord selectById(int id);
}
