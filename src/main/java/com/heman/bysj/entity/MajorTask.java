package com.heman.bysj.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 申请任务查看
 */
public class MajorTask {
    private String processInstanceId;
    private String taskId;
    private int userId;
    private String name;
    private String role;
    private String currentProfession;
    private String class_;
    private String newCollege;
    private String newProfession;
    private String reason;
    private Double gpa;
    private String rank;
    private String contest;
    private Date createTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentProfession() {
        return currentProfession;
    }

    public void setCurrentProfession(String currentProfession) {
        this.currentProfession = currentProfession;
    }

    public String getNewCollege() {
        return newCollege;
    }

    public void setNewCollege(String newCollege) {
        this.newCollege = newCollege;
    }

    public String getNewProfession() {
        return newProfession;
    }

    public void setNewProfession(String newProfession) {
        this.newProfession = newProfession;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getContest() {
        return contest;
    }

    public void setContest(String contest) {
        this.contest = contest;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
