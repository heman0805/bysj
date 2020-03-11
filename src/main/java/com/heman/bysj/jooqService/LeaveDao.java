package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.records.LeaveRecord;

public interface LeaveDao {
    int insertIntoLeave(LeaveRecord leave);
}
