package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.ExamineRecord;
import com.heman.bysj.jooqService.ExamineDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.heman.bysj.jooq.tables.Examine.EXAMINE;
@Slf4j
@Service
public class ExamineDaoImpl implements ExamineDao {
    @Autowired
    private DSLContext dslContext;
    @Override
    public void insert(ExamineRecord holidayCheck) {
        dslContext.insertInto(EXAMINE)
                .set(holidayCheck)
                .execute();
    }

    @Override
    public ExamineRecord selectByProcessInstanceId(String processInstanceId) {
        return dslContext.selectFrom(EXAMINE)
                .where(EXAMINE.PROCESSINSTANCEID.eq(processInstanceId))
                .fetchOne();
    }

    @Override
    public ExamineRecord selectByProcessInstanceIdOne(String processInstanceId) {
        List<ExamineRecord> fetch = dslContext.selectFrom(EXAMINE)
                .where(EXAMINE.PROCESSINSTANCEID.eq(processInstanceId))
                .orderBy(EXAMINE.CHECKTIME.desc())
                .fetch();
        if(fetch.size()!=0){
            ExamineRecord examineRecord = fetch.get(0);
            System.out.println("时间："+examineRecord.getChecktime());
            return examineRecord;
        }
        return null;
    }

    @Override
    public ExamineRecord selectByTaskId(String taskId) {
        return dslContext.selectFrom(EXAMINE)
                .where(EXAMINE.TASKID.eq(taskId))
                .fetchOne();
    }
}
