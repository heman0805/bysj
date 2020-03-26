/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables.records;


import com.heman.bysj.jooq.tables.ActRuTask;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record20;
import org.jooq.Row20;
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
public class ActRuTaskRecord extends UpdatableRecordImpl<ActRuTaskRecord> implements Record20<String, Integer, String, String, String, String, String, String, String, String, String, String, Integer, Timestamp, Timestamp, String, Integer, String, String, Timestamp> {

    private static final long serialVersionUID = 744403075;

    /**
     * Setter for <code>bysj.act_ru_task.ID_</code>.
     */
    public void setId_(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.ID_</code>.
     */
    public String getId_() {
        return (String) get(0);
    }

    /**
     * Setter for <code>bysj.act_ru_task.REV_</code>.
     */
    public void setRev_(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.REV_</code>.
     */
    public Integer getRev_() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>bysj.act_ru_task.EXECUTION_ID_</code>.
     */
    public void setExecutionId_(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.EXECUTION_ID_</code>.
     */
    public String getExecutionId_() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bysj.act_ru_task.PROC_INST_ID_</code>.
     */
    public void setProcInstId_(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.PROC_INST_ID_</code>.
     */
    public String getProcInstId_() {
        return (String) get(3);
    }

    /**
     * Setter for <code>bysj.act_ru_task.PROC_DEF_ID_</code>.
     */
    public void setProcDefId_(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.PROC_DEF_ID_</code>.
     */
    public String getProcDefId_() {
        return (String) get(4);
    }

    /**
     * Setter for <code>bysj.act_ru_task.NAME_</code>.
     */
    public void setName_(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.NAME_</code>.
     */
    public String getName_() {
        return (String) get(5);
    }

    /**
     * Setter for <code>bysj.act_ru_task.PARENT_TASK_ID_</code>.
     */
    public void setParentTaskId_(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.PARENT_TASK_ID_</code>.
     */
    public String getParentTaskId_() {
        return (String) get(6);
    }

    /**
     * Setter for <code>bysj.act_ru_task.DESCRIPTION_</code>.
     */
    public void setDescription_(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.DESCRIPTION_</code>.
     */
    public String getDescription_() {
        return (String) get(7);
    }

    /**
     * Setter for <code>bysj.act_ru_task.TASK_DEF_KEY_</code>.
     */
    public void setTaskDefKey_(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.TASK_DEF_KEY_</code>.
     */
    public String getTaskDefKey_() {
        return (String) get(8);
    }

    /**
     * Setter for <code>bysj.act_ru_task.OWNER_</code>.
     */
    public void setOwner_(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.OWNER_</code>.
     */
    public String getOwner_() {
        return (String) get(9);
    }

    /**
     * Setter for <code>bysj.act_ru_task.ASSIGNEE_</code>.
     */
    public void setAssignee_(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.ASSIGNEE_</code>.
     */
    public String getAssignee_() {
        return (String) get(10);
    }

    /**
     * Setter for <code>bysj.act_ru_task.DELEGATION_</code>.
     */
    public void setDelegation_(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.DELEGATION_</code>.
     */
    public String getDelegation_() {
        return (String) get(11);
    }

    /**
     * Setter for <code>bysj.act_ru_task.PRIORITY_</code>.
     */
    public void setPriority_(Integer value) {
        set(12, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.PRIORITY_</code>.
     */
    public Integer getPriority_() {
        return (Integer) get(12);
    }

    /**
     * Setter for <code>bysj.act_ru_task.CREATE_TIME_</code>.
     */
    public void setCreateTime_(Timestamp value) {
        set(13, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.CREATE_TIME_</code>.
     */
    public Timestamp getCreateTime_() {
        return (Timestamp) get(13);
    }

    /**
     * Setter for <code>bysj.act_ru_task.DUE_DATE_</code>.
     */
    public void setDueDate_(Timestamp value) {
        set(14, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.DUE_DATE_</code>.
     */
    public Timestamp getDueDate_() {
        return (Timestamp) get(14);
    }

    /**
     * Setter for <code>bysj.act_ru_task.CATEGORY_</code>.
     */
    public void setCategory_(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.CATEGORY_</code>.
     */
    public String getCategory_() {
        return (String) get(15);
    }

    /**
     * Setter for <code>bysj.act_ru_task.SUSPENSION_STATE_</code>.
     */
    public void setSuspensionState_(Integer value) {
        set(16, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.SUSPENSION_STATE_</code>.
     */
    public Integer getSuspensionState_() {
        return (Integer) get(16);
    }

    /**
     * Setter for <code>bysj.act_ru_task.TENANT_ID_</code>.
     */
    public void setTenantId_(String value) {
        set(17, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.TENANT_ID_</code>.
     */
    public String getTenantId_() {
        return (String) get(17);
    }

    /**
     * Setter for <code>bysj.act_ru_task.FORM_KEY_</code>.
     */
    public void setFormKey_(String value) {
        set(18, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.FORM_KEY_</code>.
     */
    public String getFormKey_() {
        return (String) get(18);
    }

    /**
     * Setter for <code>bysj.act_ru_task.CLAIM_TIME_</code>.
     */
    public void setClaimTime_(Timestamp value) {
        set(19, value);
    }

    /**
     * Getter for <code>bysj.act_ru_task.CLAIM_TIME_</code>.
     */
    public Timestamp getClaimTime_() {
        return (Timestamp) get(19);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record20 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row20<String, Integer, String, String, String, String, String, String, String, String, String, String, Integer, Timestamp, Timestamp, String, Integer, String, String, Timestamp> fieldsRow() {
        return (Row20) super.fieldsRow();
    }

    @Override
    public Row20<String, Integer, String, String, String, String, String, String, String, String, String, String, Integer, Timestamp, Timestamp, String, Integer, String, String, Timestamp> valuesRow() {
        return (Row20) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return ActRuTask.ACT_RU_TASK.ID_;
    }

    @Override
    public Field<Integer> field2() {
        return ActRuTask.ACT_RU_TASK.REV_;
    }

    @Override
    public Field<String> field3() {
        return ActRuTask.ACT_RU_TASK.EXECUTION_ID_;
    }

    @Override
    public Field<String> field4() {
        return ActRuTask.ACT_RU_TASK.PROC_INST_ID_;
    }

    @Override
    public Field<String> field5() {
        return ActRuTask.ACT_RU_TASK.PROC_DEF_ID_;
    }

    @Override
    public Field<String> field6() {
        return ActRuTask.ACT_RU_TASK.NAME_;
    }

    @Override
    public Field<String> field7() {
        return ActRuTask.ACT_RU_TASK.PARENT_TASK_ID_;
    }

    @Override
    public Field<String> field8() {
        return ActRuTask.ACT_RU_TASK.DESCRIPTION_;
    }

    @Override
    public Field<String> field9() {
        return ActRuTask.ACT_RU_TASK.TASK_DEF_KEY_;
    }

    @Override
    public Field<String> field10() {
        return ActRuTask.ACT_RU_TASK.OWNER_;
    }

    @Override
    public Field<String> field11() {
        return ActRuTask.ACT_RU_TASK.ASSIGNEE_;
    }

    @Override
    public Field<String> field12() {
        return ActRuTask.ACT_RU_TASK.DELEGATION_;
    }

    @Override
    public Field<Integer> field13() {
        return ActRuTask.ACT_RU_TASK.PRIORITY_;
    }

    @Override
    public Field<Timestamp> field14() {
        return ActRuTask.ACT_RU_TASK.CREATE_TIME_;
    }

    @Override
    public Field<Timestamp> field15() {
        return ActRuTask.ACT_RU_TASK.DUE_DATE_;
    }

    @Override
    public Field<String> field16() {
        return ActRuTask.ACT_RU_TASK.CATEGORY_;
    }

    @Override
    public Field<Integer> field17() {
        return ActRuTask.ACT_RU_TASK.SUSPENSION_STATE_;
    }

    @Override
    public Field<String> field18() {
        return ActRuTask.ACT_RU_TASK.TENANT_ID_;
    }

    @Override
    public Field<String> field19() {
        return ActRuTask.ACT_RU_TASK.FORM_KEY_;
    }

    @Override
    public Field<Timestamp> field20() {
        return ActRuTask.ACT_RU_TASK.CLAIM_TIME_;
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
        return getExecutionId_();
    }

    @Override
    public String component4() {
        return getProcInstId_();
    }

    @Override
    public String component5() {
        return getProcDefId_();
    }

    @Override
    public String component6() {
        return getName_();
    }

    @Override
    public String component7() {
        return getParentTaskId_();
    }

    @Override
    public String component8() {
        return getDescription_();
    }

    @Override
    public String component9() {
        return getTaskDefKey_();
    }

    @Override
    public String component10() {
        return getOwner_();
    }

    @Override
    public String component11() {
        return getAssignee_();
    }

    @Override
    public String component12() {
        return getDelegation_();
    }

    @Override
    public Integer component13() {
        return getPriority_();
    }

    @Override
    public Timestamp component14() {
        return getCreateTime_();
    }

    @Override
    public Timestamp component15() {
        return getDueDate_();
    }

    @Override
    public String component16() {
        return getCategory_();
    }

    @Override
    public Integer component17() {
        return getSuspensionState_();
    }

    @Override
    public String component18() {
        return getTenantId_();
    }

    @Override
    public String component19() {
        return getFormKey_();
    }

    @Override
    public Timestamp component20() {
        return getClaimTime_();
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
        return getExecutionId_();
    }

    @Override
    public String value4() {
        return getProcInstId_();
    }

    @Override
    public String value5() {
        return getProcDefId_();
    }

    @Override
    public String value6() {
        return getName_();
    }

    @Override
    public String value7() {
        return getParentTaskId_();
    }

    @Override
    public String value8() {
        return getDescription_();
    }

    @Override
    public String value9() {
        return getTaskDefKey_();
    }

    @Override
    public String value10() {
        return getOwner_();
    }

    @Override
    public String value11() {
        return getAssignee_();
    }

    @Override
    public String value12() {
        return getDelegation_();
    }

    @Override
    public Integer value13() {
        return getPriority_();
    }

    @Override
    public Timestamp value14() {
        return getCreateTime_();
    }

    @Override
    public Timestamp value15() {
        return getDueDate_();
    }

    @Override
    public String value16() {
        return getCategory_();
    }

    @Override
    public Integer value17() {
        return getSuspensionState_();
    }

    @Override
    public String value18() {
        return getTenantId_();
    }

    @Override
    public String value19() {
        return getFormKey_();
    }

    @Override
    public Timestamp value20() {
        return getClaimTime_();
    }

    @Override
    public ActRuTaskRecord value1(String value) {
        setId_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value2(Integer value) {
        setRev_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value3(String value) {
        setExecutionId_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value4(String value) {
        setProcInstId_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value5(String value) {
        setProcDefId_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value6(String value) {
        setName_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value7(String value) {
        setParentTaskId_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value8(String value) {
        setDescription_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value9(String value) {
        setTaskDefKey_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value10(String value) {
        setOwner_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value11(String value) {
        setAssignee_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value12(String value) {
        setDelegation_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value13(Integer value) {
        setPriority_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value14(Timestamp value) {
        setCreateTime_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value15(Timestamp value) {
        setDueDate_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value16(String value) {
        setCategory_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value17(Integer value) {
        setSuspensionState_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value18(String value) {
        setTenantId_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value19(String value) {
        setFormKey_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord value20(Timestamp value) {
        setClaimTime_(value);
        return this;
    }

    @Override
    public ActRuTaskRecord values(String value1, Integer value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, Integer value13, Timestamp value14, Timestamp value15, String value16, Integer value17, String value18, String value19, Timestamp value20) {
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
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActRuTaskRecord
     */
    public ActRuTaskRecord() {
        super(ActRuTask.ACT_RU_TASK);
    }

    /**
     * Create a detached, initialised ActRuTaskRecord
     */
    public ActRuTaskRecord(String id_, Integer rev_, String executionId_, String procInstId_, String procDefId_, String name_, String parentTaskId_, String description_, String taskDefKey_, String owner_, String assignee_, String delegation_, Integer priority_, Timestamp createTime_, Timestamp dueDate_, String category_, Integer suspensionState_, String tenantId_, String formKey_, Timestamp claimTime_) {
        super(ActRuTask.ACT_RU_TASK);

        set(0, id_);
        set(1, rev_);
        set(2, executionId_);
        set(3, procInstId_);
        set(4, procDefId_);
        set(5, name_);
        set(6, parentTaskId_);
        set(7, description_);
        set(8, taskDefKey_);
        set(9, owner_);
        set(10, assignee_);
        set(11, delegation_);
        set(12, priority_);
        set(13, createTime_);
        set(14, dueDate_);
        set(15, category_);
        set(16, suspensionState_);
        set(17, tenantId_);
        set(18, formKey_);
        set(19, claimTime_);
    }
}
