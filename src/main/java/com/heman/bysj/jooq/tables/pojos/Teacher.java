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
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1720005777;

    private Integer tid;
    private String  name;
    private String  sex;
    private String  username;
    private String  password;
    private Integer grade;
    private String  college;
    private String  profession;
    private String  position;
    private String  role;
    private String  group;

    public Teacher() {}

    public Teacher(Teacher value) {
        this.tid = value.tid;
        this.name = value.name;
        this.sex = value.sex;
        this.username = value.username;
        this.password = value.password;
        this.grade = value.grade;
        this.college = value.college;
        this.profession = value.profession;
        this.position = value.position;
        this.role = value.role;
        this.group = value.group;
    }

    public Teacher(
        Integer tid,
        String  name,
        String  sex,
        String  username,
        String  password,
        Integer grade,
        String  college,
        String  profession,
        String  position,
        String  role,
        String  group
    ) {
        this.tid = tid;
        this.name = name;
        this.sex = sex;
        this.username = username;
        this.password = password;
        this.grade = grade;
        this.college = college;
        this.profession = profession;
        this.position = position;
        this.role = role;
        this.group = group;
    }

    public Integer getTid() {
        return this.tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getCollege() {
        return this.college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Teacher (");

        sb.append(tid);
        sb.append(", ").append(name);
        sb.append(", ").append(sex);
        sb.append(", ").append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(grade);
        sb.append(", ").append(college);
        sb.append(", ").append(profession);
        sb.append(", ").append(position);
        sb.append(", ").append(role);
        sb.append(", ").append(group);

        sb.append(")");
        return sb.toString();
    }
}
