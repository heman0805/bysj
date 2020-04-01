package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.pojos.HolidayCheck;
import com.heman.bysj.jooq.tables.records.HolidayCheckRecord;
import com.heman.bysj.jooqService.HolidayCheckDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.heman.bysj.jooq.tables.HolidayCheck.HOLIDAY_CHECK;
@Slf4j
@Service
public class HolidayCheckDaoImpl implements HolidayCheckDao {
    @Autowired
    private DSLContext dslContext;
    @Override
    public void insert(HolidayCheckRecord holidayCheck) {
        dslContext.insertInto(HOLIDAY_CHECK)
                .set(holidayCheck)
                .execute();
    }
}
