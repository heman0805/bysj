package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;
import com.heman.bysj.jooqService.ChangeMajorsDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.heman.bysj.jooq.tables.Changemajors.CHANGEMAJORS;

@Slf4j
@Service
public class ChangeMajorsDaoImpl implements ChangeMajorsDao {

    @Autowired
    private DSLContext dslContext;

    /**
     * 插入学生转专业申请表
     * @param changemajorsRecord
     * @return
     */
    @Override
    public int insert(ChangemajorsRecord changemajorsRecord) {
        return dslContext
                .insertInto(CHANGEMAJORS)
                .set(changemajorsRecord)
                .execute();
    }

    @Override
    public ChangemajorsRecord selectByProcessInstanceId(String processInstanceId) {
        return dslContext.selectFrom(CHANGEMAJORS)
                .where(CHANGEMAJORS.PROCESSINSTANCEID.eq(processInstanceId))
                .fetchOne();
    }
    @Override
    public void complete(String processInstanceId) {
        dslContext.update(CHANGEMAJORS)
                .set(CHANGEMAJORS.PROCESSSTATUS,CHANGEMAJORS.PROCESSSTATUS.add(1))
                .set(CHANGEMAJORS.UPDATETIME,new Timestamp(System.currentTimeMillis()))
                .where(CHANGEMAJORS.PROCESSINSTANCEID.eq(processInstanceId))
                .execute();
    }

    @Override
    public void stopRunProcessInstance(String processInstanceId) {
        dslContext.update(CHANGEMAJORS)
                .set(CHANGEMAJORS.PROCESSSTATUS,7)
                .set(CHANGEMAJORS.UPDATETIME,new Timestamp(System.currentTimeMillis()))
                .where(CHANGEMAJORS.PROCESSINSTANCEID.eq(processInstanceId))
                .execute();
    }

    @Override
    public List<ChangemajorsRecord> selectByUserId(int userId) {
        return dslContext.selectFrom(CHANGEMAJORS)
                .where(CHANGEMAJORS.USERID.eq(userId))
                .fetch();

    }

    @Override
    public List<ChangemajorsRecord> selectByProfessionAndProcessStatus(String profession, int processStatus) {
        return dslContext.selectFrom(CHANGEMAJORS)
                .where(CHANGEMAJORS.NEWPROFESSION.eq(profession))
                .and(CHANGEMAJORS.PROCESSSTATUS.eq(processStatus))
                .fetch();
    }
    @Override
    public List<ChangemajorsRecord> selectByCollegeAndProcessStatus(String college, int processStatus) {
        return dslContext.selectFrom(CHANGEMAJORS)
                .where(CHANGEMAJORS.NEWCOLLEGE.eq(college))
                .and(CHANGEMAJORS.PROCESSSTATUS.eq(processStatus))
                .fetch();
    }

    @Override
    public List<ChangemajorsRecord> selectByCollegeAndNotComplete(String college) {
        return dslContext.selectFrom(CHANGEMAJORS)
                .where(CHANGEMAJORS.NEWCOLLEGE.eq(college))
                .and(CHANGEMAJORS.PROCESSSTATUS.notEqual(6))
                .and(CHANGEMAJORS.PROCESSSTATUS.notEqual(7))
                .and(CHANGEMAJORS.PROCESSSTATUS.notEqual(10))
                .fetch();
    }

    @Override
    public void updateProcessStatusCompleteByUserId(int userId) {
        dslContext.update(CHANGEMAJORS)
                .set(CHANGEMAJORS.PROCESSSTATUS,10)
                .where(CHANGEMAJORS.USERID.eq(userId))
                .execute();
    }

    @Override
    public List<ChangemajorsRecord> selectByProcessStatus(int processStatus) {
        return dslContext.selectFrom(CHANGEMAJORS)
                .where(CHANGEMAJORS.PROCESSSTATUS.eq(processStatus))
                .fetch();
    }
}
