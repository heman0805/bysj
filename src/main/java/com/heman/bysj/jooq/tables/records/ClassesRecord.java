/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables.records;


import com.heman.bysj.jooq.tables.Classes;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
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
public class ClassesRecord extends UpdatableRecordImpl<ClassesRecord> implements Record4<Integer, String, String, String> {

    private static final long serialVersionUID = -1577754687;

    /**
     * Setter for <code>bysj.classes.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>bysj.classes.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>bysj.classes.college</code>.
     */
    public void setCollege(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bysj.classes.college</code>.
     */
    public String getCollege() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bysj.classes.profession</code>.
     */
    public void setProfession(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bysj.classes.profession</code>.
     */
    public String getProfession() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bysj.classes.class_</code>.
     */
    public void setClass_(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bysj.classes.class_</code>.
     */
    public String getClass_() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Classes.CLASSES.ID;
    }

    @Override
    public Field<String> field2() {
        return Classes.CLASSES.COLLEGE;
    }

    @Override
    public Field<String> field3() {
        return Classes.CLASSES.PROFESSION;
    }

    @Override
    public Field<String> field4() {
        return Classes.CLASSES.CLASS_;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getCollege();
    }

    @Override
    public String component3() {
        return getProfession();
    }

    @Override
    public String component4() {
        return getClass_();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getCollege();
    }

    @Override
    public String value3() {
        return getProfession();
    }

    @Override
    public String value4() {
        return getClass_();
    }

    @Override
    public ClassesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ClassesRecord value2(String value) {
        setCollege(value);
        return this;
    }

    @Override
    public ClassesRecord value3(String value) {
        setProfession(value);
        return this;
    }

    @Override
    public ClassesRecord value4(String value) {
        setClass_(value);
        return this;
    }

    @Override
    public ClassesRecord values(Integer value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ClassesRecord
     */
    public ClassesRecord() {
        super(Classes.CLASSES);
    }

    /**
     * Create a detached, initialised ClassesRecord
     */
    public ClassesRecord(Integer id, String college, String profession, String class_) {
        super(Classes.CLASSES);

        set(0, id);
        set(1, college);
        set(2, profession);
        set(3, class_);
    }
}
