package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.entity.ChangeMajorResult;
import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooqService.StudentDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.heman.bysj.jooq.tables.Student.STUDENT;

@Slf4j
@Service
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private DSLContext dslContext ;

    /**
     *
     * @param limit:一次取出的数据数目
     * @param offest:偏移量（从第几条开始取）
     * @return
     */
    @Override
    public List<StudentRecord> listTest(int limit,int offest) {
        return dslContext.selectFrom(STUDENT)
                .limit(limit)
                .offset(offest)
                .fetch();
    }

    @Override
    public StudentRecord selectById(int id) {
        return dslContext.selectFrom(STUDENT)
                .where(STUDENT.SID.eq(id))
                .fetchOne();
    }

    @Override
    public int insertStudent(StudentRecord studentRecord) {
        return dslContext.insertInto(STUDENT)
                .set(studentRecord)
                .execute();
    }


    @Override
    public StudentRecord getStudnetByUsername(String username, String password) {
        return dslContext.selectFrom(STUDENT)
                .where(STUDENT.USERNAME.eq(username))
                .and(STUDENT.PASSWORD.eq(password))
                .fetchOne();//返回一个结果或null
    }

    @Override
    public StudentRecord getByUserName(String userName) {
        return dslContext.selectFrom(STUDENT)
                .where(STUDENT.USERNAME.eq(userName))
                .fetchOne();
    }

    @Override
    public List<StudentRecord> selectByClass(String class_) {
        return dslContext.selectFrom(STUDENT)
                .where(STUDENT.CLASS_.eq(class_))
                .fetch();
    }

    @Override
    public List<StudentRecord> selectByProfession(String profession) {
        return dslContext.selectFrom(STUDENT)
                .where(STUDENT.PROFESSION.eq(profession))
                .fetch();
    }

    @Override
    public List<StudentRecord> selectByCollege(String college) {
        return dslContext.selectFrom(STUDENT)
                .where(STUDENT.COLLEGE.eq(college))
                .fetch();
    }

    @Override
    public void updateChangeMajor(ChangeMajorResult result,int tid) {
        log.info("更新学生信息Dao");
        dslContext.update(STUDENT)
                .set(STUDENT.COLLEGE,result.getNewCollege())
                .set(STUDENT.PROFESSION,result.getNewProfession())
                .set(STUDENT.CLASS_,result.getNewClass_())
                .set(STUDENT.TID,tid)
                .where(STUDENT.SID.eq(result.getSid()))
                .execute();



    }

    @Override
    public int updatePasswordBySid(int sid, String password) {
        return dslContext.update(STUDENT)
                .set(STUDENT.PASSWORD,password)
                .where(STUDENT.SID.eq(sid))
                .execute();
    }
}
