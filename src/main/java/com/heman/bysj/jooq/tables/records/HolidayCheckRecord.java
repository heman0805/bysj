/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables.records;


import com.heman.bysj.jooq.tables.HolidayCheck;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HolidayCheckRecord extends UpdatableRecordImpl<HolidayCheckRecord> implements Record8<String, String, String, Integer, String, Byte, String, Timestamp> {

    private static final long serialVersionUID = -670455066;

    /**
     * Setter for <code>bysj.holiday_check.checkId</code>. 审批ID
     */
    public void setCheckid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>bysj.holiday_check.checkId</code>. 审批ID
     */
    public String getCheckid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>bysj.holiday_check.processInstanceId</code>. activiti流程实例ID
     */
    public void setProcessinstanceid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bysj.holiday_check.processInstanceId</code>. activiti流程实例ID
     */
    public String getProcessinstanceid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bysj.holiday_check.taskId</code>. activiti任务ID
     */
    public void setTaskid(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bysj.holiday_check.taskId</code>. activiti任务ID
     */
    public String getTaskid() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bysj.holiday_check.userID</code>. 请假用户ID
     */
    public void setUserid(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>bysj.holiday_check.userID</code>. 请假用户ID
     */
    public Integer getUserid() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>bysj.holiday_check.role</code>. 请假用户角色
     */
    public void setRole(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>bysj.holiday_check.role</code>. 请假用户角色
     */
    public String getRole() {
        return (String) get(4);
    }

    /**
     * Setter for <code>bysj.holiday_check.checkResult</code>. 审批结果：0 不通过，1 通过
     */
    public void setCheckresult(Byte value) {
        set(5, value);
    }

    /**
     * Getter for <code>bysj.holiday_check.checkResult</code>. 审批结果：0 不通过，1 通过
     */
    public Byte getCheckresult() {
        return (Byte) get(5);
    }

    /**
     * Setter for <code>bysj.holiday_check.opinion</code>. 审批意见
     */
    public void setOpinion(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>bysj.holiday_check.opinion</code>. 审批意见
     */
    public String getOpinion() {
        return (String) get(6);
    }

    /**
     * Setter for <code>bysj.holiday_check.checkTime</code>. 审批日期
     */
    public void setChecktime(Timestamp value) {
        set(7, value);
    }

    /**
     * Getter for <code>bysj.holiday_check.checkTime</code>. 审批日期
     */
    public Timestamp getChecktime() {
        return (Timestamp) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, String, String, Integer, String, Byte, String, Timestamp> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<String, String, String, Integer, String, Byte, String, Timestamp> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return HolidayCheck.HOLIDAY_CHECK.CHECKID;
    }

    @Override
    public Field<String> field2() {
        return HolidayCheck.HOLIDAY_CHECK.PROCESSINSTANCEID;
    }

    @Override
    public Field<String> field3() {
        return HolidayCheck.HOLIDAY_CHECK.TASKID;
    }

    @Override
    public Field<Integer> field4() {
        return HolidayCheck.HOLIDAY_CHECK.USERID;
    }

    @Override
    public Field<String> field5() {
        return HolidayCheck.HOLIDAY_CHECK.ROLE;
    }

    @Override
    public Field<Byte> field6() {
        return HolidayCheck.HOLIDAY_CHECK.CHECKRESULT;
    }

    @Override
    public Field<String> field7() {
        return HolidayCheck.HOLIDAY_CHECK.OPINION;
    }

    @Override
    public Field<Timestamp> field8() {
        return HolidayCheck.HOLIDAY_CHECK.CHECKTIME;
    }

    @Override
    public String component1() {
        return getCheckid();
    }

    @Override
    public String component2() {
        return getProcessinstanceid();
    }

    @Override
    public String component3() {
        return getTaskid();
    }

    @Override
    public Integer component4() {
        return getUserid();
    }

    @Override
    public String component5() {
        return getRole();
    }

    @Override
    public Byte component6() {
        return getCheckresult();
    }

    @Override
    public String component7() {
        return getOpinion();
    }

    @Override
    public Timestamp component8() {
        return getChecktime();
    }

    @Override
    public String value1() {
        return getCheckid();
    }

    @Override
    public String value2() {
        return getProcessinstanceid();
    }

    @Override
    public String value3() {
        return getTaskid();
    }

    @Override
    public Integer value4() {
        return getUserid();
    }

    @Override
    public String value5() {
        return getRole();
    }

    @Override
    public Byte value6() {
        return getCheckresult();
    }

    @Override
    public String value7() {
        return getOpinion();
    }

    @Override
    public Timestamp value8() {
        return getChecktime();
    }

    @Override
    public HolidayCheckRecord value1(String value) {
        setCheckid(value);
        return this;
    }

    @Override
    public HolidayCheckRecord value2(String value) {
        setProcessinstanceid(value);
        return this;
    }

    @Override
    public HolidayCheckRecord value3(String value) {
        setTaskid(value);
        return this;
    }

    @Override
    public HolidayCheckRecord value4(Integer value) {
        setUserid(value);
        return this;
    }

    @Override
    public HolidayCheckRecord value5(String value) {
        setRole(value);
        return this;
    }

    @Override
    public HolidayCheckRecord value6(Byte value) {
        setCheckresult(value);
        return this;
    }

    @Override
    public HolidayCheckRecord value7(String value) {
        setOpinion(value);
        return this;
    }

    @Override
    public HolidayCheckRecord value8(Timestamp value) {
        setChecktime(value);
        return this;
    }

    @Override
    public HolidayCheckRecord values(String value1, String value2, String value3, Integer value4, String value5, Byte value6, String value7, Timestamp value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HolidayCheckRecord
     */
    public HolidayCheckRecord() {
        super(HolidayCheck.HOLIDAY_CHECK);
    }

    /**
     * Create a detached, initialised HolidayCheckRecord
     */
    public HolidayCheckRecord(String checkid, String processinstanceid, String taskid, Integer userid, String role, Byte checkresult, String opinion, Timestamp checktime) {
        super(HolidayCheck.HOLIDAY_CHECK);

        set(0, checkid);
        set(1, processinstanceid);
        set(2, taskid);
        set(3, userid);
        set(4, role);
        set(5, checkresult);
        set(6, opinion);
        set(7, checktime);
    }
}
