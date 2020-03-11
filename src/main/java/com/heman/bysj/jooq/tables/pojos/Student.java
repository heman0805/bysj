/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables.pojos;


import java.io.Serializable;

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
public class Student implements Serializable {

    private static final long serialVersionUID = -9765789;

    private Integer sid;
    private String  name;
    private String  username;
    private String  password;
    private Integer grade;
    private String  profession;
    private String  class_;
    private Integer tid;

    public Student() {}

    public Student(Student value) {
        this.sid = value.sid;
        this.name = value.name;
        this.username = value.username;
        this.password = value.password;
        this.grade = value.grade;
        this.profession = value.profession;
        this.class_ = value.class_;
        this.tid = value.tid;
    }

    public Student(
        Integer sid,
        String  name,
        String  username,
        String  password,
        Integer grade,
        String  profession,
        String  class_,
        Integer tid
    ) {
        this.sid = sid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.grade = grade;
        this.profession = profession;
        this.class_ = class_;
        this.tid = tid;
    }

    public Integer getSid() {
        return this.sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getClass_() {
        return this.class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public Integer getTid() {
        return this.tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Student (");

        sb.append(sid);
        sb.append(", ").append(name);
        sb.append(", ").append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(grade);
        sb.append(", ").append(profession);
        sb.append(", ").append(class_);
        sb.append(", ").append(tid);

        sb.append(")");
        return sb.toString();
    }
}
