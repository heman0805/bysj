package com.heman.bysj.mapper;

import com.heman.bysj.model.entity.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LeaveMapper {
    void insertIntoLeave(Leave leave);
}
