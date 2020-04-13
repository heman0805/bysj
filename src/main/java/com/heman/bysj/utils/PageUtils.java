package com.heman.bysj.utils;

import java.util.ArrayList;
import java.util.List;

public class PageUtils<T> {
    private  Page toPage(List<T> result,int limit, int totalElement){
        Page page = new Page();
        page.setData(result);
        page.setSize(limit);
        page.setTotalElement(totalElement);
        return page;
    }
    public  Page resultPage(List<T> list,int pageNum,int limit){
        int offest;
        if(pageNum>0){
            offest =(pageNum-1)*limit;
        }else{
            offest=0;
        }
        Page page = new Page();
        List<T> result = new ArrayList<>();
        int toIndex = 0;
        if(list==null){
            page = toPage(null,limit,0);
            return page;
        }
        else if(offest+limit<list.size()) {
            toIndex = offest+limit;
        }else{
            toIndex = list.size();
        }
        result = list.subList(offest,toIndex);
         page = toPage(result,limit,list.size());
        return page;
    }
}
