package com.heman.bysj.service;

import com.heman.bysj.jooq.tables.records.LeaveRecord;

public interface LeaveService {
    String insertLeave(LeaveRecord leave , String param);
}
