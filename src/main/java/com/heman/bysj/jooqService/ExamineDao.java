package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.records.ExamineRecord;

public interface ExamineDao {
    void insert(ExamineRecord holidayCheck);
    ExamineRecord selectByProcessInstanceId(String processInstanceId);
    ExamineRecord selectByTaskId(String taskId);
}
