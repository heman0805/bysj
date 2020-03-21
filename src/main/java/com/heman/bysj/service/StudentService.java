package com.heman.bysj.service;

import com.heman.bysj.jooq.tables.records.StudentRecord;

import java.util.List;

public interface StudentService {

     List<StudentRecord> listTest(int limit,int offest);

     StudentRecord selectById(int id);
}
