package com.heman.bysj.service;

import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;

public interface ChangeMajorsService {
    int insert(ChangemajorsRecord changemajorsRecord);
    int updateState(int cid,String state);
    int updateResult(int cid,int result,String post,String refuseReason);
    ChangemajorsRecord selectBySid(int sid);
}
