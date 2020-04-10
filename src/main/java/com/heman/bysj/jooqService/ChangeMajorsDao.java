package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;

public interface ChangeMajorsDao {
    int insert(ChangemajorsRecord changemajorsRecord);
    ChangemajorsRecord selectByProcessInstanceId(String processInstanceId);
    void complete(String processInstanceId,int status);
}
