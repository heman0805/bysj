/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


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
public class Holiday implements Serializable {

    private static final long serialVersionUID = -538717760;

    private String    formid;
    private String    processinstanceid;
    private Integer   userid;
    private String    role;
    private Byte      days;
    private Timestamp begintime;
    private Timestamp endtime;
    private Byte      vacationtype;
    private String    reason;
    private Byte      processstatus;
    private Timestamp createtime;
    private Timestamp updatetime;

    public Holiday() {}

    public Holiday(Holiday value) {
        this.formid = value.formid;
        this.processinstanceid = value.processinstanceid;
        this.userid = value.userid;
        this.role = value.role;
        this.days = value.days;
        this.begintime = value.begintime;
        this.endtime = value.endtime;
        this.vacationtype = value.vacationtype;
        this.reason = value.reason;
        this.processstatus = value.processstatus;
        this.createtime = value.createtime;
        this.updatetime = value.updatetime;
    }

    public Holiday(
        String    formid,
        String    processinstanceid,
        Integer   userid,
        String    role,
        Byte      days,
        Timestamp begintime,
        Timestamp endtime,
        Byte      vacationtype,
        String    reason,
        Byte      processstatus,
        Timestamp createtime,
        Timestamp updatetime
    ) {
        this.formid = formid;
        this.processinstanceid = processinstanceid;
        this.userid = userid;
        this.role = role;
        this.days = days;
        this.begintime = begintime;
        this.endtime = endtime;
        this.vacationtype = vacationtype;
        this.reason = reason;
        this.processstatus = processstatus;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public String getFormid() {
        return this.formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public String getProcessinstanceid() {
        return this.processinstanceid;
    }

    public void setProcessinstanceid(String processinstanceid) {
        this.processinstanceid = processinstanceid;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Byte getDays() {
        return this.days;
    }

    public void setDays(Byte days) {
        this.days = days;
    }

    public Timestamp getBegintime() {
        return this.begintime;
    }

    public void setBegintime(Timestamp begintime) {
        this.begintime = begintime;
    }

    public Timestamp getEndtime() {
        return this.endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public Byte getVacationtype() {
        return this.vacationtype;
    }

    public void setVacationtype(Byte vacationtype) {
        this.vacationtype = vacationtype;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Byte getProcessstatus() {
        return this.processstatus;
    }

    public void setProcessstatus(Byte processstatus) {
        this.processstatus = processstatus;
    }

    public Timestamp getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Holiday (");

        sb.append(formid);
        sb.append(", ").append(processinstanceid);
        sb.append(", ").append(userid);
        sb.append(", ").append(role);
        sb.append(", ").append(days);
        sb.append(", ").append(begintime);
        sb.append(", ").append(endtime);
        sb.append(", ").append(vacationtype);
        sb.append(", ").append(reason);
        sb.append(", ").append(processstatus);
        sb.append(", ").append(createtime);
        sb.append(", ").append(updatetime);

        sb.append(")");
        return sb.toString();
    }
}