/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables;


import com.heman.bysj.jooq.Bysj;
import com.heman.bysj.jooq.Indexes;
import com.heman.bysj.jooq.Keys;
import com.heman.bysj.jooq.tables.records.StudentRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Student extends TableImpl<StudentRecord> {

    private static final long serialVersionUID = 871834436;

    /**
     * The reference instance of <code>bysj.student</code>
     */
    public static final Student STUDENT = new Student();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StudentRecord> getRecordType() {
        return StudentRecord.class;
    }

    /**
     * The column <code>bysj.student.sid</code>. 主键ID
     */
    public final TableField<StudentRecord, Integer> SID = createField(DSL.name("sid"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "主键ID");

    /**
     * The column <code>bysj.student.name</code>. 学生姓名
     */
    public final TableField<StudentRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "学生姓名");

    /**
     * The column <code>bysj.student.username</code>. 用户名
     */
    public final TableField<StudentRecord, String> USERNAME = createField(DSL.name("username"), org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "用户名");

    /**
     * The column <code>bysj.student.password</code>. 密码
     */
    public final TableField<StudentRecord, String> PASSWORD = createField(DSL.name("password"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "密码");

    /**
     * The column <code>bysj.student.grade</code>. 年级
     */
    public final TableField<StudentRecord, Integer> GRADE = createField(DSL.name("grade"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "年级");

    /**
     * The column <code>bysj.student.profession</code>. 专业
     */
    public final TableField<StudentRecord, String> PROFESSION = createField(DSL.name("profession"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "专业");

    /**
     * The column <code>bysj.student.class_</code>. 班级
     */
    public final TableField<StudentRecord, String> CLASS_ = createField(DSL.name("class_"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "班级");

    /**
     * The column <code>bysj.student.tid</code>. 辅导教师
     */
    public final TableField<StudentRecord, Integer> TID = createField(DSL.name("tid"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "辅导教师");

    /**
     * The column <code>bysj.student.role</code>. 角色：学生
     */
    public final TableField<StudentRecord, String> ROLE = createField(DSL.name("role"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "角色：学生");

    /**
     * The column <code>bysj.student.group</code>. 所属组用户：Group_Student
     */
    public final TableField<StudentRecord, String> GROUP = createField(DSL.name("group"), org.jooq.impl.SQLDataType.VARCHAR(30).nullable(false), this, "所属组用户：Group_Student");

    /**
     * Create a <code>bysj.student</code> table reference
     */
    public Student() {
        this(DSL.name("student"), null);
    }

    /**
     * Create an aliased <code>bysj.student</code> table reference
     */
    public Student(String alias) {
        this(DSL.name(alias), STUDENT);
    }

    /**
     * Create an aliased <code>bysj.student</code> table reference
     */
    public Student(Name alias) {
        this(alias, STUDENT);
    }

    private Student(Name alias, Table<StudentRecord> aliased) {
        this(alias, aliased, null);
    }

    private Student(Name alias, Table<StudentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Student(Table<O> child, ForeignKey<O, StudentRecord> key) {
        super(child, key, STUDENT);
    }

    @Override
    public Schema getSchema() {
        return Bysj.BYSJ;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.STUDENT_PRIMARY, Indexes.STUDENT_STUDENT_TID_FORIENGINKEY, Indexes.STUDENT_SUSERNAME_UNIQUE);
    }

    @Override
    public Identity<StudentRecord, Integer> getIdentity() {
        return Keys.IDENTITY_STUDENT;
    }

    @Override
    public UniqueKey<StudentRecord> getPrimaryKey() {
        return Keys.KEY_STUDENT_PRIMARY;
    }

    @Override
    public List<UniqueKey<StudentRecord>> getKeys() {
        return Arrays.<UniqueKey<StudentRecord>>asList(Keys.KEY_STUDENT_PRIMARY, Keys.KEY_STUDENT_SUSERNAME_UNIQUE);
    }

    @Override
    public List<ForeignKey<StudentRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<StudentRecord, ?>>asList(Keys.STUDENT_TID_FORIENGINKEY);
    }

    public Teacher teacher() {
        return new Teacher(this, Keys.STUDENT_TID_FORIENGINKEY);
    }

    @Override
    public Student as(String alias) {
        return new Student(DSL.name(alias), this);
    }

    @Override
    public Student as(Name alias) {
        return new Student(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Student rename(String name) {
        return new Student(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Student rename(Name name) {
        return new Student(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, String, String, String, Integer, String, String, Integer, String, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
