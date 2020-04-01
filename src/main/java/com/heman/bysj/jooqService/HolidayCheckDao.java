package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.pojos.HolidayCheck;
import com.heman.bysj.jooq.tables.records.HolidayCheckRecord;

public interface HolidayCheckDao {
    void insert(HolidayCheckRecord holidayCheck);
}
