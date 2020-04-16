package com.heman.bysj.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ColumnTitleMap {
    private Map<String, String> columnTitleMap = new HashMap<String, String>();
    private ArrayList<String> titleKeyList = new ArrayList<String> ();

    public ColumnTitleMap(String datatype) {
        switch (datatype) {
            case "changeMajor":
                initUserInfoColu();
                initUserInfoTitleKeyList();
                break;
            default:
                break;
        }

    }
    /**
     * mysql用户表需要导出字段--显示名称对应集合
     */
    private void initUserInfoColu() {
        columnTitleMap.put("id", "ID");
        columnTitleMap.put("name", "学生姓名");
        columnTitleMap.put("sex", "性别");
        columnTitleMap.put("number", "学号");
        columnTitleMap.put("grade", "年级");
        columnTitleMap.put("currentProfession", "原专业");
        columnTitleMap.put("currentClass", "原班级");
        columnTitleMap.put("newProfession", "新专业");
        columnTitleMap.put("newClass", "新班级");
    }

    /**
     * mysql用户表需要导出字段集
     */
    private void initUserInfoTitleKeyList() {
        titleKeyList.add("id");
        titleKeyList.add("name");
        titleKeyList.add("sex");
        titleKeyList.add("number");
        titleKeyList.add("grade");
        titleKeyList.add("currentProfession");
        titleKeyList.add("currentClass");
        titleKeyList.add("newProfession");
        titleKeyList.add("newClass");
    }

    public Map<String, String> getColumnTitleMap() {
        return columnTitleMap;
    }

    public ArrayList<String> getTitleKeyList() {
        return titleKeyList;
    }
}
