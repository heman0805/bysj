/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables.records;


import com.heman.bysj.jooq.tables.Teacher;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
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
public class TeacherRecord extends UpdatableRecordImpl<TeacherRecord> implements Record9<Integer, String, String, String, Integer, String, String, String, Integer> {

    private static final long serialVersionUID = 1373409374;

    /**
     * Setter for <code>bysj.teacher.tid</code>. 主键ID
     */
    public void setTid(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>bysj.teacher.tid</code>. 主键ID
     */
    public Integer getTid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>bysj.teacher.name</code>. 教师姓名
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bysj.teacher.name</code>. 教师姓名
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bysj.teacher.username</code>. 用户名
     */
    public void setUsername(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bysj.teacher.username</code>. 用户名
     */
    public String getUsername() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bysj.teacher.password</code>. 密码
     */
    public void setPassword(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bysj.teacher.password</code>. 密码
     */
    public String getPassword() {
        return (String) get(3);
    }

    /**
     * Setter for <code>bysj.teacher.grade</code>. 年级
     */
    public void setGrade(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>bysj.teacher.grade</code>. 年级
     */
    public Integer getGrade() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>bysj.teacher.college</code>. 学院名称
     */
    public void setCollege(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>bysj.teacher.college</code>. 学院名称
     */
    public String getCollege() {
        return (String) get(5);
    }

    /**
     * Setter for <code>bysj.teacher.profession</code>. 专业
     */
    public void setProfession(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>bysj.teacher.profession</code>. 专业
     */
    public String getProfession() {
        return (String) get(6);
    }

    /**
     * Setter for <code>bysj.teacher.position</code>. 职位
     */
    public void setPosition(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>bysj.teacher.position</code>. 职位
     */
    public String getPosition() {
        return (String) get(7);
    }

    /**
     * Setter for <code>bysj.teacher.superior</code>. 上级领导
     */
    public void setSuperior(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>bysj.teacher.superior</code>. 上级领导
     */
    public Integer getSuperior() {
        return (Integer) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<Integer, String, String, String, Integer, String, String, String, Integer> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<Integer, String, String, String, Integer, String, String, String, Integer> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Teacher.TEACHER.TID;
    }

    @Override
    public Field<String> field2() {
        return Teacher.TEACHER.NAME;
    }

    @Override
    public Field<String> field3() {
        return Teacher.TEACHER.USERNAME;
    }

    @Override
    public Field<String> field4() {
        return Teacher.TEACHER.PASSWORD;
    }

    @Override
    public Field<Integer> field5() {
        return Teacher.TEACHER.GRADE;
    }

    @Override
    public Field<String> field6() {
        return Teacher.TEACHER.COLLEGE;
    }

    @Override
    public Field<String> field7() {
        return Teacher.TEACHER.PROFESSION;
    }

    @Override
    public Field<String> field8() {
        return Teacher.TEACHER.POSITION;
    }

    @Override
    public Field<Integer> field9() {
        return Teacher.TEACHER.SUPERIOR;
    }

    @Override
    public Integer component1() {
        return getTid();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getUsername();
    }

    @Override
    public String component4() {
        return getPassword();
    }

    @Override
    public Integer component5() {
        return getGrade();
    }

    @Override
    public String component6() {
        return getCollege();
    }

    @Override
    public String component7() {
        return getProfession();
    }

    @Override
    public String component8() {
        return getPosition();
    }

    @Override
    public Integer component9() {
        return getSuperior();
    }

    @Override
    public Integer value1() {
        return getTid();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getUsername();
    }

    @Override
    public String value4() {
        return getPassword();
    }

    @Override
    public Integer value5() {
        return getGrade();
    }

    @Override
    public String value6() {
        return getCollege();
    }

    @Override
    public String value7() {
        return getProfession();
    }

    @Override
    public String value8() {
        return getPosition();
    }

    @Override
    public Integer value9() {
        return getSuperior();
    }

    @Override
    public TeacherRecord value1(Integer value) {
        setTid(value);
        return this;
    }

    @Override
    public TeacherRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public TeacherRecord value3(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public TeacherRecord value4(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public TeacherRecord value5(Integer value) {
        setGrade(value);
        return this;
    }

    @Override
    public TeacherRecord value6(String value) {
        setCollege(value);
        return this;
    }

    @Override
    public TeacherRecord value7(String value) {
        setProfession(value);
        return this;
    }

    @Override
    public TeacherRecord value8(String value) {
        setPosition(value);
        return this;
    }

    @Override
    public TeacherRecord value9(Integer value) {
        setSuperior(value);
        return this;
    }

    @Override
    public TeacherRecord values(Integer value1, String value2, String value3, String value4, Integer value5, String value6, String value7, String value8, Integer value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TeacherRecord
     */
    public TeacherRecord() {
        super(Teacher.TEACHER);
    }

    /**
     * Create a detached, initialised TeacherRecord
     */
    public TeacherRecord(Integer tid, String name, String username, String password, Integer grade, String college, String profession, String position, Integer superior) {
        super(Teacher.TEACHER);

        set(0, tid);
        set(1, name);
        set(2, username);
        set(3, password);
        set(4, grade);
        set(5, college);
        set(6, profession);
        set(7, position);
        set(8, superior);
    }
}