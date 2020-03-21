/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables;


import com.heman.bysj.jooq.Bysj;
import com.heman.bysj.jooq.Indexes;
import com.heman.bysj.jooq.Keys;
import com.heman.bysj.jooq.tables.records.ChangemajorsRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row11;
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
public class Changemajors extends TableImpl<ChangemajorsRecord> {

    private static final long serialVersionUID = 690382268;

    /**
     * The reference instance of <code>bysj.changemajors</code>
     */
    public static final Changemajors CHANGEMAJORS = new Changemajors();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ChangemajorsRecord> getRecordType() {
        return ChangemajorsRecord.class;
    }

    /**
     * The column <code>bysj.changemajors.cid</code>. 转专业表主键ID
     */
    public final TableField<ChangemajorsRecord, Integer> CID = createField(DSL.name("cid"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "转专业表主键ID");

    /**
     * The column <code>bysj.changemajors.sid</code>. 转专业学生ID
     */
    public final TableField<ChangemajorsRecord, Integer> SID = createField(DSL.name("sid"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "转专业学生ID");

    /**
     * The column <code>bysj.changemajors.applyReason</code>. 转专业申请理由
     */
    public final TableField<ChangemajorsRecord, String> APPLYREASON = createField(DSL.name("applyReason"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "转专业申请理由");

    /**
     * The column <code>bysj.changemajors.currentProfession</code>. 现专业
     */
    public final TableField<ChangemajorsRecord, String> CURRENTPROFESSION = createField(DSL.name("currentProfession"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "现专业");

    /**
     * The column <code>bysj.changemajors.newProfession</code>. 新专业
     */
    public final TableField<ChangemajorsRecord, String> NEWPROFESSION = createField(DSL.name("newProfession"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "新专业");

    /**
     * The column <code>bysj.changemajors.gpa</code>. 学生个人GPA
     */
    public final TableField<ChangemajorsRecord, Double> GPA = createField(DSL.name("gpa"), org.jooq.impl.SQLDataType.FLOAT.nullable(false), this, "学生个人GPA");

    /**
     * The column <code>bysj.changemajors.rank</code>. 成绩排名
     */
    public final TableField<ChangemajorsRecord, String> RANK = createField(DSL.name("rank"), org.jooq.impl.SQLDataType.VARCHAR(5).nullable(false), this, "成绩排名");

    /**
     * The column <code>bysj.changemajors.state</code>. 申请状态（如：当前专业院长审批中）
     */
    public final TableField<ChangemajorsRecord, String> STATE = createField(DSL.name("state"), org.jooq.impl.SQLDataType.VARCHAR(15).nullable(false), this, "申请状态（如：当前专业院长审批中）");

    /**
     * The column <code>bysj.changemajors.result</code>. 转专业申请结果(0:不通过 1:通过)
     */
    public final TableField<ChangemajorsRecord, Integer> RESULT = createField(DSL.name("result"), org.jooq.impl.SQLDataType.INTEGER, this, "转专业申请结果(0:不通过 1:通过)");

    /**
     * The column <code>bysj.changemajors.post</code>. 申请过程中拒绝的环节（原专业院长）
     */
    public final TableField<ChangemajorsRecord, String> POST = createField(DSL.name("post"), org.jooq.impl.SQLDataType.VARCHAR(10), this, "申请过程中拒绝的环节（原专业院长）");

    /**
     * The column <code>bysj.changemajors.refuseReason</code>. 拒绝理由
     */
    public final TableField<ChangemajorsRecord, String> REFUSEREASON = createField(DSL.name("refuseReason"), org.jooq.impl.SQLDataType.VARCHAR(100), this, "拒绝理由");

    /**
     * Create a <code>bysj.changemajors</code> table reference
     */
    public Changemajors() {
        this(DSL.name("changemajors"), null);
    }

    /**
     * Create an aliased <code>bysj.changemajors</code> table reference
     */
    public Changemajors(String alias) {
        this(DSL.name(alias), CHANGEMAJORS);
    }

    /**
     * Create an aliased <code>bysj.changemajors</code> table reference
     */
    public Changemajors(Name alias) {
        this(alias, CHANGEMAJORS);
    }

    private Changemajors(Name alias, Table<ChangemajorsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Changemajors(Name alias, Table<ChangemajorsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Changemajors(Table<O> child, ForeignKey<O, ChangemajorsRecord> key) {
        super(child, key, CHANGEMAJORS);
    }

    @Override
    public Schema getSchema() {
        return Bysj.BYSJ;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CHANGEMAJORS_PRIMARY, Indexes.CHANGEMAJORS_SID);
    }

    @Override
    public Identity<ChangemajorsRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CHANGEMAJORS;
    }

    @Override
    public UniqueKey<ChangemajorsRecord> getPrimaryKey() {
        return Keys.KEY_CHANGEMAJORS_PRIMARY;
    }

    @Override
    public List<UniqueKey<ChangemajorsRecord>> getKeys() {
        return Arrays.<UniqueKey<ChangemajorsRecord>>asList(Keys.KEY_CHANGEMAJORS_PRIMARY, Keys.KEY_CHANGEMAJORS_SID);
    }

    @Override
    public List<ForeignKey<ChangemajorsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ChangemajorsRecord, ?>>asList(Keys.CHANGESID_FOREIGNKEY);
    }

    public Student student() {
        return new Student(this, Keys.CHANGESID_FOREIGNKEY);
    }

    @Override
    public Changemajors as(String alias) {
        return new Changemajors(DSL.name(alias), this);
    }

    @Override
    public Changemajors as(Name alias) {
        return new Changemajors(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Changemajors rename(String name) {
        return new Changemajors(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Changemajors rename(Name name) {
        return new Changemajors(name, null);
    }

    // -------------------------------------------------------------------------
    // Row11 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row11<Integer, Integer, String, String, String, Double, String, String, Integer, String, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }
}
