/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq.tables.daos;


import com.heman.bysj.jooq.tables.Leave;
import com.heman.bysj.jooq.tables.records.LeaveRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class LeaveDao extends DAOImpl<LeaveRecord, com.heman.bysj.jooq.tables.pojos.Leave, Integer> {

    /**
     * Create a new LeaveDao without any configuration
     */
    public LeaveDao() {
        super(Leave.LEAVE, com.heman.bysj.jooq.tables.pojos.Leave.class);
    }

    /**
     * Create a new LeaveDao with an attached configuration
     */
    public LeaveDao(Configuration configuration) {
        super(Leave.LEAVE, com.heman.bysj.jooq.tables.pojos.Leave.class, configuration);
    }

    @Override
    public Integer getId(com.heman.bysj.jooq.tables.pojos.Leave object) {
        return object.getLid();
    }

    /**
     * Fetch records that have <code>lid BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfLid(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Leave.LEAVE.LID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>lid IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByLid(Integer... values) {
        return fetch(Leave.LEAVE.LID, values);
    }

    /**
     * Fetch a unique record that has <code>lid = value</code>
     */
    public com.heman.bysj.jooq.tables.pojos.Leave fetchOneByLid(Integer value) {
        return fetchOne(Leave.LEAVE.LID, value);
    }

    /**
     * Fetch records that have <code>nid BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfNid(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Leave.LEAVE.NID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>nid IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByNid(Integer... values) {
        return fetch(Leave.LEAVE.NID, values);
    }

    /**
     * Fetch records that have <code>nTime BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfNtime(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Leave.LEAVE.NTIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>nTime IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByNtime(Timestamp... values) {
        return fetch(Leave.LEAVE.NTIME, values);
    }

    /**
     * Fetch records that have <code>sTime BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfStime(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Leave.LEAVE.STIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sTime IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByStime(Timestamp... values) {
        return fetch(Leave.LEAVE.STIME, values);
    }

    /**
     * Fetch records that have <code>eTime BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfEtime(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Leave.LEAVE.ETIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>eTime IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByEtime(Timestamp... values) {
        return fetch(Leave.LEAVE.ETIME, values);
    }

    /**
     * Fetch records that have <code>reason BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfReason(String lowerInclusive, String upperInclusive) {
        return fetchRange(Leave.LEAVE.REASON, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>reason IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByReason(String... values) {
        return fetch(Leave.LEAVE.REASON, values);
    }

    /**
     * Fetch records that have <code>type BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfType(String lowerInclusive, String upperInclusive) {
        return fetchRange(Leave.LEAVE.TYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByType(String... values) {
        return fetch(Leave.LEAVE.TYPE, values);
    }

    /**
     * Fetch records that have <code>result BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfResult(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Leave.LEAVE.RESULT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>result IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByResult(Integer... values) {
        return fetch(Leave.LEAVE.RESULT, values);
    }

    /**
     * Fetch records that have <code>remark BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfRemark(String lowerInclusive, String upperInclusive) {
        return fetchRange(Leave.LEAVE.REMARK, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>remark IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByRemark(String... values) {
        return fetch(Leave.LEAVE.REMARK, values);
    }

    /**
     * Fetch records that have <code>role BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfRole(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Leave.LEAVE.ROLE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>role IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByRole(Integer... values) {
        return fetch(Leave.LEAVE.ROLE, values);
    }

    /**
     * Fetch records that have <code>tid BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchRangeOfTid(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Leave.LEAVE.TID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>tid IN (values)</code>
     */
    public List<com.heman.bysj.jooq.tables.pojos.Leave> fetchByTid(Integer... values) {
        return fetch(Leave.LEAVE.TID, values);
    }
}