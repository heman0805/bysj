package com.heman.bysj.service;

import com.heman.bysj.entity.HolidayByClass;
import com.heman.bysj.entity.HolidayHistory;
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
    List<HolidayHistory> holidayHistory(int userId,String role);
    List<String> selectClassByProfession(String profession);
    List<String> selectProfessionByCollege(String college);
    List<HolidayByClass> selectHolidayByClass(int tid,String param,String class_);
    List<HolidayByClass> searchTeacherHoliday(String param,String colpro);
}
