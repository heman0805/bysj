package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.records.StudentRecord;

import java.util.List;

public interface StudentDao {

    //获取学生列表
    List<StudentRecord> listTest();

    //根据ID获取学生信息
    StudentRecord selectById(int id);
}
