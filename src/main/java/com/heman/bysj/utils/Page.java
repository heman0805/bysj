package com.heman.bysj.utils;

import java.util.List;

public class Page<T> {
    private List<T> data;       //当前返回数据
    private int totalElement;  //数据总条数
    private int size;          //每页条数

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
