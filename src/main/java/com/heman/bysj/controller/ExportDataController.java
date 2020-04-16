package com.heman.bysj.controller;


import com.heman.bysj.entity.ChangeMajorResult;
import com.heman.bysj.service.ChangeMajorsService;
import com.heman.bysj.utils.ColumnTitleMap;
import com.heman.bysj.utils.Convert;
import com.heman.bysj.utils.ExportExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping()
public class ExportDataController {
    @Autowired
    private ChangeMajorsService changeMajorsService;

    @RequestMapping(value = "/template")
    public void userListTemplate(HttpServletResponse response,
                                 @RequestParam String college) {


        try {
            log.info("college=:"+college);
            List<ChangeMajorResult> holidayByClasses = changeMajorsService.selectMajor(college);
            System.out.println("转换前数据："+holidayByClasses.get(0).getNewClass_());
            List<Map<String, Object>> src_list = Convert.listResultToMap(holidayByClasses);
            System.out.println("转换后数据："+src_list.toString());
            ArrayList<String> titleKeyList= new ColumnTitleMap("changeMajor").getTitleKeyList();
            Map<String, String> titleMap = new ColumnTitleMap("changeMajor").getColumnTitleMap();
            ExportExcelUtils.userListTemplate(response, titleKeyList, titleMap, src_list);
        } catch (Exception e) {
            log.info(e.toString());
        }
    }
}

