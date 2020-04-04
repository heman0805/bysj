package com.heman.bysj.jooqService;


import com.heman.bysj.jooq.tables.pojos.Holiday;
import com.heman.bysj.jooq.tables.records.HolidayRecord;

import java.util.List;

public interface HolidayDao {

    HolidayRecord selectByFormId(String id);
    HolidayRecord selectByProcessInstanceId(String processInstanceId);
    int insert(Holiday holiday);
    void complete(String processInstanceId,int processStatus);
    List<HolidayRecord> selectByUserIdAndRoleAndProcessStatus(int userId,String role);
}
