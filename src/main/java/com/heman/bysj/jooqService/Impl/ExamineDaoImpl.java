package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.ExamineRecord;
import com.heman.bysj.jooqService.ExamineDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
