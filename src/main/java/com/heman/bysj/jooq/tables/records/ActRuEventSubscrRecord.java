/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables.records;


import com.heman.bysj.jooq.tables.ActRuEventSubscr;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
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
public class ActRuEventSubscrRecord extends UpdatableRecordImpl<ActRuEventSubscrRecord> implements Record11<String, Integer, String, String, String, String, String, String, Timestamp, String, String> {

    private static final long serialVersionUID = 1452985317;

    /**
     * Setter for <code>bysj.act_ru_event_subscr.ID_</code>.
     */
    public void setId_(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.ID_</code>.
     */
    public String getId_() {
        return (String) get(0);
    }

    /**
     * Setter for <code>bysj.act_ru_event_subscr.REV_</code>.
     */
    public void setRev_(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.REV_</code>.
     */
    public Integer getRev_() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>bysj.act_ru_event_subscr.EVENT_TYPE_</code>.
     */
    public void setEventType_(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.EVENT_TYPE_</code>.
     */
    public String getEventType_() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bysj.act_ru_event_subscr.EVENT_NAME_</code>.
     */
    public void setEventName_(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.EVENT_NAME_</code>.
     */
    public String getEventName_() {
        return (String) get(3);
    }

    /**
     * Setter for <code>bysj.act_ru_event_subscr.EXECUTION_ID_</code>.
     */
    public void setExecutionId_(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.EXECUTION_ID_</code>.
     */
    public String getExecutionId_() {
        return (String) get(4);
    }

    /**
     * Setter for <code>bysj.act_ru_event_subscr.PROC_INST_ID_</code>.
     */
    public void setProcInstId_(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.PROC_INST_ID_</code>.
     */
    public String getProcInstId_() {
        return (String) get(5);
    }

    /**
     * Setter for <code>bysj.act_ru_event_subscr.ACTIVITY_ID_</code>.
     */
    public void setActivityId_(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.ACTIVITY_ID_</code>.
     */
    public String getActivityId_() {
        return (String) get(6);
    }

    /**
     * Setter for <code>bysj.act_ru_event_subscr.CONFIGURATION_</code>.
     */
    public void setConfiguration_(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.CONFIGURATION_</code>.
     */
    public String getConfiguration_() {
        return (String) get(7);
    }

    /**
     * Setter for <code>bysj.act_ru_event_subscr.CREATED_</code>.
     */
    public void setCreated_(Timestamp value) {
        set(8, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.CREATED_</code>.
     */
    public Timestamp getCreated_() {
        return (Timestamp) get(8);
    }

    /**
     * Setter for <code>bysj.act_ru_event_subscr.PROC_DEF_ID_</code>.
     */
    public void setProcDefId_(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.PROC_DEF_ID_</code>.
     */
    public String getProcDefId_() {
        return (String) get(9);
    }

    /**
     * Setter for <code>bysj.act_ru_event_subscr.TENANT_ID_</code>.
     */
    public void setTenantId_(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>bysj.act_ru_event_subscr.TENANT_ID_</code>.
     */
    public String getTenantId_() {
        return (String) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row11<String, Integer, String, String, String, String, String, String, Timestamp, String, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<String, Integer, String, String, String, String, String, String, Timestamp, String, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.ID_;
    }

    @Override
    public Field<Integer> field2() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.REV_;
    }

    @Override
    public Field<String> field3() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.EVENT_TYPE_;
    }

    @Override
    public Field<String> field4() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.EVENT_NAME_;
    }

    @Override
    public Field<String> field5() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.EXECUTION_ID_;
    }

    @Override
    public Field<String> field6() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.PROC_INST_ID_;
    }

    @Override
    public Field<String> field7() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.ACTIVITY_ID_;
    }

    @Override
    public Field<String> field8() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.CONFIGURATION_;
    }

    @Override
    public Field<Timestamp> field9() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.CREATED_;
    }

    @Override
    public Field<String> field10() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.PROC_DEF_ID_;
    }

    @Override
    public Field<String> field11() {
        return ActRuEventSubscr.ACT_RU_EVENT_SUBSCR.TENANT_ID_;
    }

    @Override
    public String component1() {
        return getId_();
    }

    @Override
    public Integer component2() {
        return getRev_();
    }

    @Override
    public String component3() {
        return getEventType_();
    }

    @Override
    public String component4() {
        return getEventName_();
    }

    @Override
    public String component5() {
        return getExecutionId_();
    }

    @Override
    public String component6() {
        return getProcInstId_();
    }

    @Override
    public String component7() {
        return getActivityId_();
    }

    @Override
    public String component8() {
        return getConfiguration_();
    }

    @Override
    public Timestamp component9() {
        return getCreated_();
    }

    @Override
    public String component10() {
        return getProcDefId_();
    }

    @Override
    public String component11() {
        return getTenantId_();
    }

    @Override
    public String value1() {
        return getId_();
    }

    @Override
    public Integer value2() {
        return getRev_();
    }

    @Override
    public String value3() {
        return getEventType_();
    }

    @Override
    public String value4() {
        return getEventName_();
    }

    @Override
    public String value5() {
        return getExecutionId_();
    }

    @Override
    public String value6() {
        return getProcInstId_();
    }

    @Override
    public String value7() {
        return getActivityId_();
    }

    @Override
    public String value8() {
        return getConfiguration_();
    }

    @Override
    public Timestamp value9() {
        return getCreated_();
    }

    @Override
    public String value10() {
        return getProcDefId_();
    }

    @Override
    public String value11() {
        return getTenantId_();
    }

    @Override
    public ActRuEventSubscrRecord value1(String value) {
        setId_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord value2(Integer value) {
        setRev_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord value3(String value) {
        setEventType_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord value4(String value) {
        setEventName_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord value5(String value) {
        setExecutionId_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord value6(String value) {
        setProcInstId_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord value7(String value) {
        setActivityId_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord value8(String value) {
        setConfiguration_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord value9(Timestamp value) {
        setCreated_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord value10(String value) {
        setProcDefId_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord value11(String value) {
        setTenantId_(value);
        return this;
    }

    @Override
    public ActRuEventSubscrRecord values(String value1, Integer value2, String value3, String value4, String value5, String value6, String value7, String value8, Timestamp value9, String value10, String value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActRuEventSubscrRecord
     */
    public ActRuEventSubscrRecord() {
        super(ActRuEventSubscr.ACT_RU_EVENT_SUBSCR);
    }

    /**
     * Create a detached, initialised ActRuEventSubscrRecord
     */
    public ActRuEventSubscrRecord(String id_, Integer rev_, String eventType_, String eventName_, String executionId_, String procInstId_, String activityId_, String configuration_, Timestamp created_, String procDefId_, String tenantId_) {
        super(ActRuEventSubscr.ACT_RU_EVENT_SUBSCR);

        set(0, id_);
        set(1, rev_);
        set(2, eventType_);
        set(3, eventName_);
        set(4, executionId_);
        set(5, procInstId_);
        set(6, activityId_);
        set(7, configuration_);
        set(8, created_);
        set(9, procDefId_);
        set(10, tenantId_);
    }
}
