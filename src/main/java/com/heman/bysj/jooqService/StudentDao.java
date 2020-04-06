package com.heman.bysj.jooqService;

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
    //查询总学生数
    int countStudent();
    //插入学生信息
    int insertStudent(StudentRecord studentRecord);
    //修改学生信息
    int updateStudent(StudentRecord studentRecord);
    //通过ID删除学生信息
    int deleteById(int id);
    //通过用户名密码获取学生信息
    StudentRecord getStudnetByUsername(String username,String password);
    Result<Record1<Integer>> selectIdsByClass(String class_);
    List<StudentRecord> selectByClass(String class_);
    List<StudentRecord> selectByProfession(String profession);
}
