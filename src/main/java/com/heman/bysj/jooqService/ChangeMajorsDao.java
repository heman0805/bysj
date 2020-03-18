package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;

public interface ChangeMajorsDao {
    int insert(ChangemajorsRecord changemajorsRecord);
    int updateState(int cid,String state);
    int updateResult(int cid,int result,String post,String refuseReason);
    ChangemajorsRecord selectBySid(int sid);

}
