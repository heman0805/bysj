package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.jooqService.TeacherDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.heman.bysj.jooq.tables.Teacher.TEACHER;

@Service
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    private DSLContext dslContext;
    /**
     * 通过专业及职位查询教师信息
     * @param profession
     * @param position
     * @return
     */
    @Override
    public TeacherRecord selectByProfessionAndPosition(String profession, String position) {
        return dslContext.selectFrom(TEACHER)
                .where(TEACHER.PROFESSION.eq(profession))
                .and(TEACHER.POSITION.eq(position))
                .fetchOne();
    }
    /**
     * 通过ID查询教师信息
     * @param id
     * @return
     */
    @Override
    public TeacherRecord selectById(int id) {
        return dslContext.selectFrom(TEACHER)
                .where(TEACHER.TID.eq(id))
                .fetchOne();
    }

    @Override
    public int insertTeacher(TeacherRecord teacherRecord) {
        System.out.println("插入教师表");
        return dslContext.insertInto(TEACHER)
                .set(teacherRecord)
                .execute();
    }

    @Override
    public int updatePasswordByTid(int tid, String password) {
        return dslContext.update(TEACHER)
                .set(TEACHER.PASSWORD,password)
                .where(TEACHER.TID.eq(tid))
                .execute();
    }

    @Override
    public TeacherRecord getTeacherByUsername(String userName, String password) {
        return dslContext.selectFrom(TEACHER)
                .where(TEACHER.USERNAME.eq(userName))
                .and(TEACHER.PASSWORD.eq(password))
                .fetchOne();
    }

    @Override
    public TeacherRecord selectByProfessionAndPositionAndGrade(String profession, String position, int grade) {
        return dslContext.selectFrom(TEACHER)
                .where(TEACHER.PROFESSION.eq(profession))
                .and(TEACHER.POSITION.eq(position))
                .and(TEACHER.GRADE.eq(grade))
                .fetchOne();
    }

    @Override
    public TeacherRecord selectByParam(String grade, String college, String profession, String position) {

        if(grade!=null&&grade!=""){
            int gra;
            gra = Integer.parseInt(grade);
            return dslContext.selectFrom(TEACHER)
                    .where(TEACHER.GRADE.eq(gra))
                    .and(TEACHER.COLLEGE.eq(college))
                    .and(TEACHER.PROFESSION.eq(profession))
                    .and(TEACHER.POSITION.eq(position))
                    .fetchOne();
        }else{
            return dslContext.selectFrom(TEACHER)
                    .where(TEACHER.COLLEGE.eq(college))
                    .and(TEACHER.PROFESSION.eq(profession))
                    .and(TEACHER.POSITION.eq(position))
                    .fetchOne();
        }
    }

    @Override
    public TeacherRecord getByUserName(String userName) {
        return dslContext.selectFrom(TEACHER)
                .where(TEACHER.USERNAME.eq(userName))
                .fetchOne();
    }

    @Override
    public List<TeacherRecord> selectByCollege(String college) {
        return dslContext.selectFrom(TEACHER)
                .where(TEACHER.COLLEGE.eq(college))
                .fetch();
    }

    @Override
    public List<TeacherRecord> selectByProfession(String profession) {
        return dslContext.selectFrom(TEACHER)
                .where(TEACHER.PROFESSION.eq(profession))
                .fetch();
    }
}
