package com.heman.bysj.jooqService.Impl;

import com.heman.bysj.jooq.tables.records.ClassesRecord;
import com.heman.bysj.jooqService.ClassesDao;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.SynthScrollBarUI;

import static com.heman.bysj.jooq.tables.Classes.CLASSES;

import java.util.List;

@Service
public class ClassesDaoImpl implements ClassesDao {
    @Autowired
    private DSLContext dslContext;

    @Override
    public List<ClassesRecord> selectClassByProfession(String profession) {
        return dslContext.selectFrom(CLASSES)
                .where(CLASSES.PROFESSION.eq(profession))
                .fetch();
    }

    @Override
    public Result<Record1<String>> selectProfessionByCollege(String college) {
        Result<Record1<String>> fetch = dslContext.selectDistinct(CLASSES.PROFESSION).from(CLASSES)
                .where(CLASSES.COLLEGE.eq(college))
                .fetch();

        return fetch;
    }
}
