package com.heman.bysj.entity;

import java.util.Date;

/**
 * 转专业申请进度实体类
 */
public class MajorProgress {
    private String processInstanceId;
    private String taskId;
    private String newCollege;
    private String newProfession;
    private String taskStatus;
    private String checkResult;
    private String opinion;
    private Date checkTime;

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
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

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}
