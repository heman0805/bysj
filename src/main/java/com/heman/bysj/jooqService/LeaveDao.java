package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.records.LeaveRecord;

import java.util.List;

public interface LeaveDao {
    int insertIntoLeave(LeaveRecord leave);

    List<LeaveRecord> selectListByTid(int tid);

    LeaveRecord selectByLid(int lid);

    int updateResultAndRemark(int lid, int result,String remark);
}
