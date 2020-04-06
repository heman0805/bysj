package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.pojos.Holiday;
import com.heman.bysj.jooq.tables.records.HolidayRecord;
import com.heman.bysj.jooqService.HolidayDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.heman.bysj.jooq.tables.Holiday.HOLIDAY;

@Slf4j
@Service
public class HolidayDaoImpl implements HolidayDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public HolidayRecord selectByFormId(String id) {
        return dslContext.selectFrom(HOLIDAY)
                .where(HOLIDAY.FORMID.eq(id))
                .fetchOne();
    }

    @Override
    public HolidayRecord selectByProcessInstanceId(String processInstanceId) {
        return dslContext.selectFrom(HOLIDAY)
                .where(HOLIDAY.PROCESSINSTANCEID.eq(processInstanceId))
                .orderBy(HOLIDAY.BEGINTIME.desc())
                .fetchOne();
    }

    @Override
    public int insert(Holiday holiday) {
        HolidayRecord holidayRecord = new HolidayRecord();
        holidayRecord.from(holiday);
        return dslContext.insertInto(HOLIDAY)
                .set(holidayRecord)
                .execute();
    }

    @Override
    public void complete(String processInstanceId,int processStatus) {
        dslContext.update(HOLIDAY)
                .set(HOLIDAY.PROCESSSTATUS,processStatus)
                .set(HOLIDAY.UPDATETIME,new Timestamp(System.currentTimeMillis()))
                .where(HOLIDAY.PROCESSINSTANCEID.eq(processInstanceId))
                .execute();
    }

    @Override
    public List<HolidayRecord> selectByUserIdAndRoleAndProcessStatus(int userId, String role) {
         List<HolidayRecord> list = dslContext.selectFrom(HOLIDAY)
                .where(HOLIDAY.USERID.eq(userId))
                .and(HOLIDAY.ROLE.eq(role))
                 .and(HOLIDAY.PROCESSSTATUS.eq(2))//请假状态为已完成
                .fetch();
         return list;
    }

    @Override
    public List<HolidayRecord> selectByUidAndRole(int id,String role) {
        return dslContext.selectFrom(HOLIDAY)
                .where(HOLIDAY.USERID.eq(id))
                .and(HOLIDAY.ROLE.eq(role))
                .fetch();
    }
}
