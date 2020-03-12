package com.heman.bysj.service;

import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooq.tables.records.TeacherRecord;

public interface UserService {
    int insertIntoStudent(StudentRecord studentRecord);
    int updateStudent(StudentRecord studentRecord);
    int deleteStudent(int id);
    StudentRecord selectStudentById(int id);

    int insertIntoTeacher(TeacherRecord teacherRecord);
    int updateTeacher(TeacherRecord teacherRecord);
    int deleteTeacher(int id);
    TeacherRecord selectTeacherById(int id);
}
