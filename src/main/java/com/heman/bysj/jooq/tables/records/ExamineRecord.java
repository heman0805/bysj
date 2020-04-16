/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables.records;


import com.heman.bysj.jooq.tables.Examine;

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
public class ExamineRecord extends UpdatableRecordImpl<ExamineRecord> implements Record8<String, String, String, Integer, String, String, String, Timestamp> {

    private static final long serialVersionUID = 1130708116;

    /**
     * Setter for <code>bysj.examine.checkId</code>. 审批ID
     */
    public void setCheckid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>bysj.examine.checkId</code>. 审批ID
     */
    public String getCheckid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>bysj.examine.processInstanceId</code>. activiti流程实例ID
     */
    public void setProcessinstanceid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bysj.examine.processInstanceId</code>. activiti流程实例ID
     */
    public String getProcessinstanceid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bysj.examine.taskId</code>. activiti任务ID
     */
    public void setTaskid(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bysj.examine.taskId</code>. activiti任务ID
     */
    public String getTaskid() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bysj.examine.userID</code>. 请假用户ID
     */
    public void setUserid(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>bysj.examine.userID</code>. 请假用户ID
     */
    public Integer getUserid() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>bysj.examine.role</code>. 请假用户角色
     */
    public void setRole(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>bysj.examine.role</code>. 请假用户角色
     */
    public String getRole() {
        return (String) get(4);
    }

    /**
     * Setter for <code>bysj.examine.checkResult</code>. 审批结果：0 不通过，1 通过
     */
    public void setCheckresult(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>bysj.examine.checkResult</code>. 审批结果：0 不通过，1 通过
     */
    public String getCheckresult() {
        return (String) get(5);
    }

    /**
     * Setter for <code>bysj.examine.opinion</code>. 审批意见
     */
    public void setOpinion(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>bysj.examine.opinion</code>. 审批意见
     */
    public String getOpinion() {
        return (String) get(6);
    }

    /**
     * Setter for <code>bysj.examine.checkTime</code>. 审批日期
     */
    public void setChecktime(Timestamp value) {
        set(7, value);
    }

    /**
     * Getter for <code>bysj.examine.checkTime</code>. 审批日期
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
    public Row8<String, String, String, Integer, String, String, String, Timestamp> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<String, String, String, Integer, String, String, String, Timestamp> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Examine.EXAMINE.CHECKID;
    }

    @Override
    public Field<String> field2() {
        return Examine.EXAMINE.PROCESSINSTANCEID;
    }

    @Override
    public Field<String> field3() {
        return Examine.EXAMINE.TASKID;
    }

    @Override
    public Field<Integer> field4() {
        return Examine.EXAMINE.USERID;
    }

    @Override
    public Field<String> field5() {
        return Examine.EXAMINE.ROLE;
    }

    @Override
    public Field<String> field6() {
        return Examine.EXAMINE.CHECKRESULT;
    }

    @Override
    public Field<String> field7() {
        return Examine.EXAMINE.OPINION;
    }

    @Override
    public Field<Timestamp> field8() {
        return Examine.EXAMINE.CHECKTIME;
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
    public String component6() {
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
    public String value6() {
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
    public ExamineRecord value1(String value) {
        setCheckid(value);
        return this;
    }

    @Override
    public ExamineRecord value2(String value) {
        setProcessinstanceid(value);
        return this;
    }

    @Override
    public ExamineRecord value3(String value) {
        setTaskid(value);
        return this;
    }

    @Override
    public ExamineRecord value4(Integer value) {
        setUserid(value);
        return this;
    }

    @Override
    public ExamineRecord value5(String value) {
        setRole(value);
        return this;
    }

    @Override
    public ExamineRecord value6(String value) {
        setCheckresult(value);
        return this;
    }

    @Override
    public ExamineRecord value7(String value) {
        setOpinion(value);
        return this;
    }

    @Override
    public ExamineRecord value8(Timestamp value) {
        setChecktime(value);
        return this;
    }

    @Override
    public ExamineRecord values(String value1, String value2, String value3, Integer value4, String value5, String value6, String value7, Timestamp value8) {
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
     * Create a detached ExamineRecord
     */
    public ExamineRecord() {
        super(Examine.EXAMINE);
    }

    /**
     * Create a detached, initialised ExamineRecord
     */
    public ExamineRecord(String checkid, String processinstanceid, String taskid, Integer userid, String role, String checkresult, String opinion, Timestamp checktime) {
        super(Examine.EXAMINE);

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