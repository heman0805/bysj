package com.heman.bysj.entity;

/**
 * 转专业审批结果实体类
 */
public class ChangeMajorResult {
    private String name;//学生姓名
    private String number;//学生学号
    private String sex;
    private String class_;
    private String currentProfession;
    private String newCollege;
    private String newProfession;
    private String newClass_;
    private double gpa;
    private String processStatus;
    private String updateTime;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
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

    public String getNewClass_() {
        return newClass_;
    }

    public void setNewClass_(String newClass_) {
        this.newClass_ = newClass_;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
