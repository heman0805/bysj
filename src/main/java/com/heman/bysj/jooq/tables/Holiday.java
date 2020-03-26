/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables;


import com.heman.bysj.jooq.Bysj;
import com.heman.bysj.jooq.Indexes;
import com.heman.bysj.jooq.Keys;
import com.heman.bysj.jooq.tables.records.HolidayRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row12;
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
public class Holiday extends TableImpl<HolidayRecord> {

    private static final long serialVersionUID = -1783891468;

    /**
     * The reference instance of <code>bysj.holiday</code>
     */
    public static final Holiday HOLIDAY = new Holiday();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HolidayRecord> getRecordType() {
        return HolidayRecord.class;
    }

    /**
     * The column <code>bysj.holiday.formId</code>. 表单ID
     */
    public final TableField<HolidayRecord, String> FORMID = createField(DSL.name("formId"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "表单ID");

    /**
     * The column <code>bysj.holiday.processInstanceId</code>. activiti流程实例ID
     */
    public final TableField<HolidayRecord, String> PROCESSINSTANCEID = createField(DSL.name("processInstanceId"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "activiti流程实例ID");

    /**
     * The column <code>bysj.holiday.userId</code>. 用户ID
     */
    public final TableField<HolidayRecord, Integer> USERID = createField(DSL.name("userId"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "用户ID");

    /**
     * The column <code>bysj.holiday.role</code>. 角色：学生，教师
     */
    public final TableField<HolidayRecord, String> ROLE = createField(DSL.name("role"), org.jooq.impl.SQLDataType.VARCHAR(10).nullable(false), this, "角色：学生，教师");

    /**
     * The column <code>bysj.holiday.days</code>. 请假天数
     */
    public final TableField<HolidayRecord, Byte> DAYS = createField(DSL.name("days"), org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "请假天数");

    /**
     * The column <code>bysj.holiday.beginTime</code>. 开始时间
     */
    public final TableField<HolidayRecord, Timestamp> BEGINTIME = createField(DSL.name("beginTime"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0000-00-00 00:00:00", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "开始时间");

    /**
     * The column <code>bysj.holiday.endTime</code>. 结束日期
     */
    public final TableField<HolidayRecord, Timestamp> ENDTIME = createField(DSL.name("endTime"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0000-00-00 00:00:00", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "结束日期");

    /**
     * The column <code>bysj.holiday.vacationType</code>. 请假类型：0 病假，1 事假
     */
    public final TableField<HolidayRecord, Byte> VACATIONTYPE = createField(DSL.name("vacationType"), org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "请假类型：0 病假，1 事假");

    /**
     * The column <code>bysj.holiday.reason</code>. 请假事由
     */
    public final TableField<HolidayRecord, String> REASON = createField(DSL.name("reason"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "请假事由");

    /**
     * The column <code>bysj.holiday.processStatus</code>. 流程状态：0 申请，1 审批中，2 已完成
     */
    public final TableField<HolidayRecord, Byte> PROCESSSTATUS = createField(DSL.name("processStatus"), org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "流程状态：0 申请，1 审批中，2 已完成");

    /**
     * The column <code>bysj.holiday.createTime</code>. 创建时间
     */
    public final TableField<HolidayRecord, Timestamp> CREATETIME = createField(DSL.name("createTime"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0000-00-00 00:00:00", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "创建时间");

    /**
     * The column <code>bysj.holiday.updateTime</code>. 更新时间
     */
    public final TableField<HolidayRecord, Timestamp> UPDATETIME = createField(DSL.name("updateTime"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0000-00-00 00:00:00", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "更新时间");

    /**
     * Create a <code>bysj.holiday</code> table reference
     */
    public Holiday() {
        this(DSL.name("holiday"), null);
    }

    /**
     * Create an aliased <code>bysj.holiday</code> table reference
     */
    public Holiday(String alias) {
        this(DSL.name(alias), HOLIDAY);
    }

    /**
     * Create an aliased <code>bysj.holiday</code> table reference
     */
    public Holiday(Name alias) {
        this(alias, HOLIDAY);
    }

    private Holiday(Name alias, Table<HolidayRecord> aliased) {
        this(alias, aliased, null);
    }

    private Holiday(Name alias, Table<HolidayRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Holiday(Table<O> child, ForeignKey<O, HolidayRecord> key) {
        super(child, key, HOLIDAY);
    }

    @Override
    public Schema getSchema() {
        return Bysj.BYSJ;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.HOLIDAY_PRIMARY);
    }

    @Override
    public UniqueKey<HolidayRecord> getPrimaryKey() {
        return Keys.KEY_HOLIDAY_PRIMARY;
    }

    @Override
    public List<UniqueKey<HolidayRecord>> getKeys() {
        return Arrays.<UniqueKey<HolidayRecord>>asList(Keys.KEY_HOLIDAY_PRIMARY);
    }

    @Override
    public Holiday as(String alias) {
        return new Holiday(DSL.name(alias), this);
    }

    @Override
    public Holiday as(Name alias) {
        return new Holiday(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Holiday rename(String name) {
        return new Holiday(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Holiday rename(Name name) {
        return new Holiday(name, null);
    }

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row12<String, String, Integer, String, Byte, Timestamp, Timestamp, Byte, String, Byte, Timestamp, Timestamp> fieldsRow() {
        return (Row12) super.fieldsRow();
    }
}
