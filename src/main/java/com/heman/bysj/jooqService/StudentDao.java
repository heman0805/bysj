package com.heman.bysj.jooqService;

import com.heman.bysj.entity.ChangeMajorResult;
import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import org.jooq.Record1;
import org.jooq.Result;

import java.util.List;

public interface StudentDao {

    //获取学生列表
    List<StudentRecord> listTest(int limit,int offest);
    //根据ID获取学生信息
    StudentRecord selectById(int id);
    //插入学生信息
    int insertStudent(StudentRecord studentRecord);
    //通过用户名密码获取学生信息
    StudentRecord getStudnetByUsername(String username,String password);
    StudentRecord getByUserName(String userName);
    List<StudentRecord> selectByClass(String class_);
    List<StudentRecord> selectByProfession(String profession);
    List<StudentRecord> selectByCollege(String college);

    void updateChangeMajor(ChangeMajorResult result,int tid);
    int updatePasswordBySid(int sid,String password);
}
