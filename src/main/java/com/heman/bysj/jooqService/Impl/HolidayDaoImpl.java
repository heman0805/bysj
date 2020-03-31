package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.pojos.Holiday;
import com.heman.bysj.jooq.tables.records.HolidayRecord;
import com.heman.bysj.jooqService.HolidayDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.heman.bysj.jooq.tables.Holiday.HOLIDAY;

@Slf4j
@Service
public class HolidayDaoImpl implements HolidayDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public HolidayRecord selectByFormId(String id) {
        return dslContext.selectFrom(HOLIDAY)
                .where(HOLIDAY.FORMID.eq(id))
                .fetchOne();
    }

    @Override
    public HolidayRecord selectByProcessInstanceId(String processInstanceId) {
        return dslContext.selectFrom(HOLIDAY)
                .where(HOLIDAY.PROCESSINSTANCEID.eq(processInstanceId))
                .orderBy(HOLIDAY.BEGINTIME.desc())
                .fetchOne();
    }

    @Override
    public int insert(Holiday holiday) {
        HolidayRecord holidayRecord = new HolidayRecord();
        holidayRecord.from(holiday);
        return dslContext.insertInto(HOLIDAY)
                .set(holidayRecord)
                .execute();
    }
}
