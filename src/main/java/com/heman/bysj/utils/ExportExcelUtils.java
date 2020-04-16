package com.heman.bysj.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExportExcelUtils {
    public static void userListTemplate(HttpServletResponse response, ArrayList<String> titleKeyList,
                                        Map<String, String> titleMap, List<Map<String,Object>> src_list)
            throws IOException {
        try {
            //创建文件
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("mobileList");

            XSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.LEFT);//文字居左
            style.setVerticalAlignment(VerticalAlignment.BOTTOM);// 设置单元格垂直方向对齐方式
            style.setBorderBottom(BorderStyle.THIN);//下边框
            style.setBorderLeft(BorderStyle.THIN);//左边框
            style.setBorderRight(BorderStyle.THIN);//右边框
            style.setBorderTop(BorderStyle.THIN); //上边框

            for (int i = 0; i <=src_list.size(); i++) { //行
                XSSFRow row =	sheet.createRow(i);
                for (int j = 0; j < titleKeyList.size(); j++) {//列
                    row.createCell(j).setCellStyle(style);
                    System.out.println("第"+j+"列");
                }
                System.out.println("第"+i+"行");
            }

            //设置表头
            XSSFRow title = sheet.getRow(0);
            for (int i=0;i<titleKeyList.size();i++) {
                title.getCell(i).setCellValue(titleMap.get(titleKeyList.get(i)));
            }

            //设置表内每行数据
            for (int i=0;i<src_list.size();i++){//行
                Map<String,Object> temp = src_list.get(i);

                XSSFRow row = sheet.getRow(i+1);
                for(int j=0;j<src_list.get(i).size();j++){//列
                    row.getCell(j).setCellValue(temp.get(titleKeyList.get(j)).toString());
                }
            }

            //设置Header并且输出文件
            String fielName = System.currentTimeMillis()+".xlsx";
            response.setHeader("Content-type","application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            //设置响应头部，以及文件名进行中文防止乱码转码操作
            response.setHeader("Content-Disposition","attachment;filename="+fielName);
            wb.write(response.getOutputStream());
            wb.close();

        } catch (Exception e) {
            log.error("下载模板错误",e);
        }

    }
}