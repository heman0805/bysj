package com.heman.bysj.model.entity;


import java.sql.Timestamp;

public class Leave {
    private int lid;     //请假表主键ID
    private int nid;     //请假人信息ID
    private Timestamp nTime;//请假当前时间
    private Timestamp sTime;//请假开始时间
    private Timestamp eTime;//请假结束时间
    private String reason;  //请假原因
    private String type;    //请假类型
    private int role;   //请假人职位 0：教师 1：学生
    private int result;     //审批结果
    private String remark;  //审批备注
    private int tid;     //审批教师ID

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public Timestamp getnTime() {
        return nTime;
    }

    public void setnTime(Timestamp nTime) {
        this.nTime = nTime;
    }

    public Timestamp getsTime() {
        return sTime;
    }

    public void setsTime(Timestamp sTime) {
        this.sTime = sTime;
    }

    public Timestamp geteTime() {
        return eTime;
    }

    public void seteTime(Timestamp eTime) {
        this.eTime = eTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getrole() {
        return role;
    }

    public void setrole(int role) {
        this.role = role;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
