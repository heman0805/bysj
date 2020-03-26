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
public class ActReProcdef implements Serializable {

    private static final long serialVersionUID = 822139168;

    private String  id_;
    private Integer rev_;
    private String  category_;
    private String  name_;
    private String  key_;
    private Integer version_;
    private String  deploymentId_;
    private String  resourceName_;
    private String  dgrmResourceName_;
    private String  description_;
    private Byte    hasStartFormKey_;
    private Byte    hasGraphicalNotation_;
    private Integer suspensionState_;
    private String  tenantId_;
    private String  engineVersion_;

    public ActReProcdef() {}

    public ActReProcdef(ActReProcdef value) {
        this.id_ = value.id_;
        this.rev_ = value.rev_;
        this.category_ = value.category_;
        this.name_ = value.name_;
        this.key_ = value.key_;
        this.version_ = value.version_;
        this.deploymentId_ = value.deploymentId_;
        this.resourceName_ = value.resourceName_;
        this.dgrmResourceName_ = value.dgrmResourceName_;
        this.description_ = value.description_;
        this.hasStartFormKey_ = value.hasStartFormKey_;
        this.hasGraphicalNotation_ = value.hasGraphicalNotation_;
        this.suspensionState_ = value.suspensionState_;
        this.tenantId_ = value.tenantId_;
        this.engineVersion_ = value.engineVersion_;
    }

    public ActReProcdef(
        String  id_,
        Integer rev_,
        String  category_,
        String  name_,
        String  key_,
        Integer version_,
        String  deploymentId_,
        String  resourceName_,
        String  dgrmResourceName_,
        String  description_,
        Byte    hasStartFormKey_,
        Byte    hasGraphicalNotation_,
        Integer suspensionState_,
        String  tenantId_,
        String  engineVersion_
    ) {
        this.id_ = id_;
        this.rev_ = rev_;
        this.category_ = category_;
        this.name_ = name_;
        this.key_ = key_;
        this.version_ = version_;
        this.deploymentId_ = deploymentId_;
        this.resourceName_ = resourceName_;
        this.dgrmResourceName_ = dgrmResourceName_;
        this.description_ = description_;
        this.hasStartFormKey_ = hasStartFormKey_;
        this.hasGraphicalNotation_ = hasGraphicalNotation_;
        this.suspensionState_ = suspensionState_;
        this.tenantId_ = tenantId_;
        this.engineVersion_ = engineVersion_;
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

    public String getCategory_() {
        return this.category_;
    }

    public void setCategory_(String category_) {
        this.category_ = category_;
    }

    public String getName_() {
        return this.name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getKey_() {
        return this.key_;
    }

    public void setKey_(String key_) {
        this.key_ = key_;
    }

    public Integer getVersion_() {
        return this.version_;
    }

    public void setVersion_(Integer version_) {
        this.version_ = version_;
    }

    public String getDeploymentId_() {
        return this.deploymentId_;
    }

    public void setDeploymentId_(String deploymentId_) {
        this.deploymentId_ = deploymentId_;
    }

    public String getResourceName_() {
        return this.resourceName_;
    }

    public void setResourceName_(String resourceName_) {
        this.resourceName_ = resourceName_;
    }

    public String getDgrmResourceName_() {
        return this.dgrmResourceName_;
    }

    public void setDgrmResourceName_(String dgrmResourceName_) {
        this.dgrmResourceName_ = dgrmResourceName_;
    }

    public String getDescription_() {
        return this.description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public Byte getHasStartFormKey_() {
        return this.hasStartFormKey_;
    }

    public void setHasStartFormKey_(Byte hasStartFormKey_) {
        this.hasStartFormKey_ = hasStartFormKey_;
    }

    public Byte getHasGraphicalNotation_() {
        return this.hasGraphicalNotation_;
    }

    public void setHasGraphicalNotation_(Byte hasGraphicalNotation_) {
        this.hasGraphicalNotation_ = hasGraphicalNotation_;
    }

    public Integer getSuspensionState_() {
        return this.suspensionState_;
    }

    public void setSuspensionState_(Integer suspensionState_) {
        this.suspensionState_ = suspensionState_;
    }

    public String getTenantId_() {
        return this.tenantId_;
    }

    public void setTenantId_(String tenantId_) {
        this.tenantId_ = tenantId_;
    }

    public String getEngineVersion_() {
        return this.engineVersion_;
    }

    public void setEngineVersion_(String engineVersion_) {
        this.engineVersion_ = engineVersion_;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ActReProcdef (");

        sb.append(id_);
        sb.append(", ").append(rev_);
        sb.append(", ").append(category_);
        sb.append(", ").append(name_);
        sb.append(", ").append(key_);
        sb.append(", ").append(version_);
        sb.append(", ").append(deploymentId_);
        sb.append(", ").append(resourceName_);
        sb.append(", ").append(dgrmResourceName_);
        sb.append(", ").append(description_);
        sb.append(", ").append(hasStartFormKey_);
        sb.append(", ").append(hasGraphicalNotation_);
        sb.append(", ").append(suspensionState_);
        sb.append(", ").append(tenantId_);
        sb.append(", ").append(engineVersion_);

        sb.append(")");
        return sb.toString();
    }
}
