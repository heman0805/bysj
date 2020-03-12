package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.StudentRecord;
import com.heman.bysj.jooqService.StudentDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.heman.bysj.jooq.tables.Student.STUDENT;

@Service
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private DSLContext dslContext ;

    @Override
    public List<StudentRecord> listTest() {
        return dslContext.selectFrom(STUDENT).fetch();
    }

    @Override
    public StudentRecord selectById(int id) {
        return dslContext.selectFrom(STUDENT)
                .where(STUDENT.TID.eq(id))
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
}
