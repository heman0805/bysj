package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;
import com.heman.bysj.jooqService.ChangeMajorsDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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
    public void complete(String processInstanceId,int processStatus) {
        dslContext.update(CHANGEMAJORS)
                .set(CHANGEMAJORS.PROCESSSTATUS,processStatus)
                .set(CHANGEMAJORS.UPDATETIME,new Timestamp(System.currentTimeMillis()))
                .where(CHANGEMAJORS.PROCESSINSTANCEID.eq(processInstanceId))
                .execute();
    }
}
