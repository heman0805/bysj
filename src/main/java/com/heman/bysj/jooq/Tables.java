/*
 * This file is generated by jOOQ.
 */
package com.heman.bysj.jooq;


import com.heman.bysj.jooq.tables.Leave;
import com.heman.bysj.jooq.tables.Student;
import com.heman.bysj.jooq.tables.Teacher;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in bysj
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>bysj.leave</code>.
     */
    public static final Leave LEAVE = Leave.LEAVE;

    /**
     * The table <code>bysj.student</code>.
     */
    public static final Student STUDENT = Student.STUDENT;

    /**
     * The table <code>bysj.teacher</code>.
     */
    public static final Teacher TEACHER = Teacher.TEACHER;
}
