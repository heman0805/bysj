package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.TeacherRecord;

import java.util.List;

public interface TeacherDao {
    //通过专业及职位进行查询
    TeacherRecord selectByProfessionAndPosition(String profession,  String position);
    //通过ID进行查询
    TeacherRecord selectById(int id);
    //插入教师信息
    int insertTeacher(TeacherRecord teacherRecord);

    int updatePasswordByTid(int tid,String password);
    TeacherRecord getTeacherByUsername(String userName,String password);
    TeacherRecord getByUserName(String userName);
    TeacherRecord selectByProfessionAndPositionAndGrade(String profession,  String position,int grade);
    TeacherRecord selectByParam(String grade,String college,String profession,String position);
    List<TeacherRecord> selectByCollege(String college);
    List<TeacherRecord> selectByProfession(String profession);

}
