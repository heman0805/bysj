package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.pojos.Student;
import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooqService.StudentDao;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.heman.bysj.jooq.tables.Student.STUDENT;

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
    public int updateStudent(StudentRecord studentRecord) {
        return dslContext.update(STUDENT)
                .set(studentRecord)
                .where(STUDENT.SID.eq(studentRecord.getSid()))
                .execute();
    }

    @Override
    public int deleteById(int id) {
        return dslContext.deleteFrom(STUDENT)
                .where(STUDENT.SID.eq(id))
                .execute();
    }

    @Override
    public int countStudent() {

        return dslContext.selectCount()
                .from(STUDENT)
                .fetchOne(0,int.class);
    }

    @Override
    public StudentRecord getStudnetByUsername(String username, String password) {
        return dslContext.selectFrom(STUDENT)
                .where(STUDENT.USERNAME.eq(username))
                .and(STUDENT.PASSWORD.eq(password))
                .fetchOne();//返回一个结果或null
    }

    @Override
    public Result<Record1<Integer>> selectIdsByClass(String class_) {
        Result<Record1<Integer>> s =  dslContext.select(STUDENT.SID)
                .from(STUDENT)
                .where(STUDENT.CLASS_.eq(class_))
                .fetch();
        return s;
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
}
