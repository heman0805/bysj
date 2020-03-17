package com.heman.bysj.service;

import com.heman.bysj.jooq.tables.records.LeaveRecord;

import java.util.List;

public interface LeaveService {
    String insertLeave(LeaveRecord leave , String param);
    List<LeaveRecord> leaveList (int tid);
    LeaveRecord selectLeaveByLid(int lid);
    int updateLeaveByLid(int lid,int result,String remark);
}
