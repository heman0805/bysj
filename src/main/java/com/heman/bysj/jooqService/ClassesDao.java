package com.heman.bysj.jooqService;

import com.heman.bysj.jooq.tables.records.ClassesRecord;
import org.jooq.Record1;
import org.jooq.Result;

import java.util.List;

public interface ClassesDao {
    List<ClassesRecord> selectClassByProfession(String profession);
    Result<Record1<String>> selectProfessionByCollege(String college);
    Result<Record1<String>> selectAllCollege();
}
