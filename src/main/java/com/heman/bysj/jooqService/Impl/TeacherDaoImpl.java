package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.TeacherRecord;
import com.heman.bysj.jooqService.TeacherDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return dslContext.insertInto(TEACHER)
                .set(teacherRecord)
                .execute();
    }
    @Override
    public int updateTeacher(TeacherRecord teacherRecord) {
        return dslContext.update(TEACHER)
                .set(teacherRecord)
                .where(TEACHER.TID.eq(teacherRecord.getTid()))
                .execute();
    }
    @Override
    public int deleteById(int id) {
        return dslContext.deleteFrom(TEACHER)
                .where(TEACHER.TID.eq(id))
                .execute();
    }


}
