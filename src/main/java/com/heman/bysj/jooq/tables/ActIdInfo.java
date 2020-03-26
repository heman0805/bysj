/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables;


import com.heman.bysj.jooq.Bysj;
import com.heman.bysj.jooq.Indexes;
import com.heman.bysj.jooq.Keys;
import com.heman.bysj.jooq.tables.records.ActIdInfoRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
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
public class ActIdInfo extends TableImpl<ActIdInfoRecord> {

    private static final long serialVersionUID = 233388795;

    /**
     * The reference instance of <code>bysj.act_id_info</code>
     */
    public static final ActIdInfo ACT_ID_INFO = new ActIdInfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ActIdInfoRecord> getRecordType() {
        return ActIdInfoRecord.class;
    }

    /**
     * The column <code>bysj.act_id_info.ID_</code>.
     */
    public final TableField<ActIdInfoRecord, String> ID_ = createField(DSL.name("ID_"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>bysj.act_id_info.REV_</code>.
     */
    public final TableField<ActIdInfoRecord, Integer> REV_ = createField(DSL.name("REV_"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>bysj.act_id_info.USER_ID_</code>.
     */
    public final TableField<ActIdInfoRecord, String> USER_ID_ = createField(DSL.name("USER_ID_"), org.jooq.impl.SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>bysj.act_id_info.TYPE_</code>.
     */
    public final TableField<ActIdInfoRecord, String> TYPE_ = createField(DSL.name("TYPE_"), org.jooq.impl.SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>bysj.act_id_info.KEY_</code>.
     */
    public final TableField<ActIdInfoRecord, String> KEY_ = createField(DSL.name("KEY_"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>bysj.act_id_info.VALUE_</code>.
     */
    public final TableField<ActIdInfoRecord, String> VALUE_ = createField(DSL.name("VALUE_"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>bysj.act_id_info.PASSWORD_</code>.
     */
    public final TableField<ActIdInfoRecord, byte[]> PASSWORD_ = createField(DSL.name("PASSWORD_"), org.jooq.impl.SQLDataType.BLOB, this, "");

    /**
     * The column <code>bysj.act_id_info.PARENT_ID_</code>.
     */
    public final TableField<ActIdInfoRecord, String> PARENT_ID_ = createField(DSL.name("PARENT_ID_"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>bysj.act_id_info</code> table reference
     */
    public ActIdInfo() {
        this(DSL.name("act_id_info"), null);
    }

    /**
     * Create an aliased <code>bysj.act_id_info</code> table reference
     */
    public ActIdInfo(String alias) {
        this(DSL.name(alias), ACT_ID_INFO);
    }

    /**
     * Create an aliased <code>bysj.act_id_info</code> table reference
     */
    public ActIdInfo(Name alias) {
        this(alias, ACT_ID_INFO);
    }

    private ActIdInfo(Name alias, Table<ActIdInfoRecord> aliased) {
        this(alias, aliased, null);
    }

    private ActIdInfo(Name alias, Table<ActIdInfoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> ActIdInfo(Table<O> child, ForeignKey<O, ActIdInfoRecord> key) {
        super(child, key, ACT_ID_INFO);
    }

    @Override
    public Schema getSchema() {
        return Bysj.BYSJ;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ACT_ID_INFO_PRIMARY);
    }

    @Override
    public UniqueKey<ActIdInfoRecord> getPrimaryKey() {
        return Keys.KEY_ACT_ID_INFO_PRIMARY;
    }

    @Override
    public List<UniqueKey<ActIdInfoRecord>> getKeys() {
        return Arrays.<UniqueKey<ActIdInfoRecord>>asList(Keys.KEY_ACT_ID_INFO_PRIMARY);
    }

    @Override
    public ActIdInfo as(String alias) {
        return new ActIdInfo(DSL.name(alias), this);
    }

    @Override
    public ActIdInfo as(Name alias) {
        return new ActIdInfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ActIdInfo rename(String name) {
        return new ActIdInfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ActIdInfo rename(Name name) {
        return new ActIdInfo(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, Integer, String, String, String, String, byte[], String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}