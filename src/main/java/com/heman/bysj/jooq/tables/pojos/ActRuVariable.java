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
public class ActRuVariable implements Serializable {

    private static final long serialVersionUID = -1801467590;

    private String  id_;
    private Integer rev_;
    private String  type_;
    private String  name_;
    private String  executionId_;
    private String  procInstId_;
    private String  taskId_;
    private String  bytearrayId_;
    private Double  double_;
    private Long    long_;
    private String  text_;
    private String  text2_;

    public ActRuVariable() {}

    public ActRuVariable(ActRuVariable value) {
        this.id_ = value.id_;
        this.rev_ = value.rev_;
        this.type_ = value.type_;
        this.name_ = value.name_;
        this.executionId_ = value.executionId_;
        this.procInstId_ = value.procInstId_;
        this.taskId_ = value.taskId_;
        this.bytearrayId_ = value.bytearrayId_;
        this.double_ = value.double_;
        this.long_ = value.long_;
        this.text_ = value.text_;
        this.text2_ = value.text2_;
    }

    public ActRuVariable(
        String  id_,
        Integer rev_,
        String  type_,
        String  name_,
        String  executionId_,
        String  procInstId_,
        String  taskId_,
        String  bytearrayId_,
        Double  double_,
        Long    long_,
        String  text_,
        String  text2_
    ) {
        this.id_ = id_;
        this.rev_ = rev_;
        this.type_ = type_;
        this.name_ = name_;
        this.executionId_ = executionId_;
        this.procInstId_ = procInstId_;
        this.taskId_ = taskId_;
        this.bytearrayId_ = bytearrayId_;
        this.double_ = double_;
        this.long_ = long_;
        this.text_ = text_;
        this.text2_ = text2_;
    }

    public String getId_() {
        return this.id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public Integer getRev_() {
        return this.rev_;
    }

    public void setRev_(Integer rev_) {
        this.rev_ = rev_;
    }

    public String getType_() {
        return this.type_;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    public String getName_() {
        return this.name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getExecutionId_() {
        return this.executionId_;
    }

    public void setExecutionId_(String executionId_) {
        this.executionId_ = executionId_;
    }

    public String getProcInstId_() {
        return this.procInstId_;
    }

    public void setProcInstId_(String procInstId_) {
        this.procInstId_ = procInstId_;
    }

    public String getTaskId_() {
        return this.taskId_;
    }

    public void setTaskId_(String taskId_) {
        this.taskId_ = taskId_;
    }

    public String getBytearrayId_() {
        return this.bytearrayId_;
    }

    public void setBytearrayId_(String bytearrayId_) {
        this.bytearrayId_ = bytearrayId_;
    }

    public Double getDouble_() {
        return this.double_;
    }

    public void setDouble_(Double double_) {
        this.double_ = double_;
    }

    public Long getLong_() {
        return this.long_;
    }

    public void setLong_(Long long_) {
        this.long_ = long_;
    }

    public String getText_() {
        return this.text_;
    }

    public void setText_(String text_) {
        this.text_ = text_;
    }

    public String getText2_() {
        return this.text2_;
    }

    public void setText2_(String text2_) {
        this.text2_ = text2_;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ActRuVariable (");

        sb.append(id_);
        sb.append(", ").append(rev_);
        sb.append(", ").append(type_);
        sb.append(", ").append(name_);
        sb.append(", ").append(executionId_);
        sb.append(", ").append(procInstId_);
        sb.append(", ").append(taskId_);
        sb.append(", ").append(bytearrayId_);
        sb.append(", ").append(double_);
        sb.append(", ").append(long_);
        sb.append(", ").append(text_);
        sb.append(", ").append(text2_);

        sb.append(")");
        return sb.toString();
    }
}
