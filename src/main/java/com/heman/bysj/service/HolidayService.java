package com.heman.bysj.service;

import com.heman.bysj.entity.HolidayTask;
import com.heman.bysj.jooq.tables.pojos.Holiday;
import com.heman.bysj.jooq.tables.pojos.HolidayCheck;
import com.heman.bysj.jooq.tables.pojos.Teacher;
import com.heman.bysj.jooq.tables.records.LeaveRecord;

import java.util.List;
import java.util.Map;

public interface HolidayService {

    boolean startHoliday(Holiday holiday);
    Map<String,List> userSearch(String userId, String role);
    Holiday selectHolidayByProcessInstanceId(String processInstanceId);
    List<HolidayTask> teacherSeacherTaskList(Teacher teacher);
    void holiday_Check(HolidayCheck holidayCheck);
}
