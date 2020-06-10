package com.bc.admin.util;

import com.bc.admin.model.report.OrderTrade;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import java.math.BigDecimal;
import java.util.List;

public class ExeclUtil {

    public static void createSumExportHead(XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(0);

        XSSFCell cell = row.createCell(0);
        cell.setCellValue("商户账号");
        sheet.setColumnWidth(0, 14*256);

        cell = row.createCell(1);
        cell.setCellValue("商户名称");
        sheet.setColumnWidth(1, 28*256);

        cell = row.createCell(2);
        cell.setCellValue("收款账号");
        sheet.setColumnWidth(2, 28*256);

        cell = row.createCell(3);
        cell.setCellValue("成功笔数");
        sheet.setColumnWidth(3, 14*256);

        cell = row.createCell(4);
        cell.setCellValue("成功交易额(元)");
        sheet.setColumnWidth(4, 14*256);

        cell = row.createCell(5);
        cell.setCellValue("成功扣款额(元)");
        sheet.setColumnWidth(5, 14*256);

        cell = row.createCell(6);
        cell.setCellValue("失败笔数");
        sheet.setColumnWidth(6, 18*256);

        cell = row.createCell(7);
        cell.setCellValue("失败交易金额(元)");
        sheet.setColumnWidth(7, 18*256);

        cell = row.createCell(8);
        cell.setCellValue("应付客户总金额(元)");
        sheet.setColumnWidth(8, 18*256);

        cell = row.createCell(9);
        cell.setCellValue("应付上游总金额(元)");
        sheet.setColumnWidth(9, 18*256);

        cell = row.createCell(10);
        cell.setCellValue("总收入金额(元)");
        sheet.setColumnWidth(10, 18*256);

        cell = row.createCell(11);
        cell.setCellValue("总入账费用(元)");
        sheet.setColumnWidth(11, 18*256);
    }

    public static void createFinanceExportHead(XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(0);

        XSSFCell cell = row.createCell(0);
        cell.setCellValue("商户账号");
        sheet.setColumnWidth(0, 14*256);

        cell = row.createCell(1);
        cell.setCellValue("商户名称");
        sheet.setColumnWidth(1, 28*256);

        cell = row.createCell(2);
        cell.setCellValue("成功总笔数");
        sheet.setColumnWidth(2, 14*256);

        cell = row.createCell(3);
        cell.setCellValue("成功总交易额(元)");
        sheet.setColumnWidth(3, 14*256);

        cell = row.createCell(4);
        cell.setCellValue("转账金额(元)");
        sheet.setColumnWidth(4, 14*256);

        cell = row.createCell(5);
        cell.setCellValue("费率");
        sheet.setColumnWidth(5, 18*256);

        cell = row.createCell(6);
        cell.setCellValue("上游金额(元)");
        sheet.setColumnWidth(6, 18*256);

        cell = row.createCell(7);
        cell.setCellValue("入账金额(元)");
        sheet.setColumnWidth(7, 18*256);

        cell = row.createCell(8);
        cell.setCellValue("上游渠道名");
        sheet.setColumnWidth(8, 18*256);

    }

    public static void createSumExportContext(XSSFWorkbook workbook, XSSFSheet sheet, List<OrderTrade> orderList) {

        long stotal=0;
        BigDecimal smoney=new BigDecimal(0);
        BigDecimal soutmoney=new BigDecimal(0);
        BigDecimal sInmoney=new BigDecimal(0);
        long ftotal=0;
        BigDecimal fmoney=new BigDecimal(0);
        BigDecimal succOutMoney=new BigDecimal(0);
        BigDecimal profitTotal = new BigDecimal(0);
        BigDecimal inPriceTotal = new BigDecimal(0);
        for(int i=1;i<orderList.size()+1;i++)
        {
            OrderTrade order = orderList.get(i-1);
            XSSFRow row = sheet.createRow(i);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(order.getAgtPhone());
            cell = row.createCell(1);
            cell.setCellValue(order.getAgtName());
            cell = row.createCell(2);
            cell.setCellValue(order.getChargeAddr());
            cell = row.createCell(3);
            cell.setCellValue(order.getSuccTotal());
            cell = row.createCell(4);
            cell.setCellValue(order.getSuccTotalPrice()+"");
            cell = row.createCell(5);
            cell.setCellValue(order.getSucctotalOutMoney()+"");
            cell = row.createCell(6);
            cell.setCellValue(order.getFailTotal());
            cell = row.createCell(7);
            cell.setCellValue(order.getFailTotalPrice()+"");
            cell = row.createCell(8);
            cell.setCellValue(order.getSucctotalOutMoney()+"");
            cell = row.createCell(9);
            cell.setCellValue(order.getSucctotalInMoney()+"");
            cell = row.createCell(10);
            cell.setCellValue(order.getSuccTotalPrice()+"");
            cell = row.createCell(11);
            cell.setCellValue(order.getProfit()+"");
            stotal+=order.getSuccTotal();
            smoney=smoney.add(order.getSuccTotalPrice());
            soutmoney=soutmoney.add(order.getSucctotalOutMoney());
            sInmoney=sInmoney.add(order.getSucctotalInMoney());
            ftotal+=order.getFailTotal();
            fmoney=fmoney.add(order.getFailTotalPrice());
            succOutMoney=succOutMoney.add(order.getSucctotalOutMoney());
            profitTotal=profitTotal.add(order.getProfit());
            inPriceTotal=inPriceTotal.add(order.getSuccTotalPrice());
        }
        XSSFRow row = sheet.createRow(orderList.size()+2);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);//设置字体大小
        style.setFont(font);

        XSSFCell cel0 = row.createCell(0);
        cel0.setCellValue("总计");
        cel0.setCellStyle(style);

        cel0 = row.createCell(3);
        cel0.setCellValue(stotal);
        cel0.setCellStyle(style);

        cel0 = row.createCell(4);
        cel0.setCellValue(smoney+"");
        cel0.setCellStyle(style);

        cel0 = row.createCell(5);
        cel0.setCellValue(soutmoney+"");
        cel0.setCellStyle(style);

        cel0 = row.createCell(6);
        cel0.setCellValue(ftotal+"");
        cel0.setCellStyle(style);

        cel0 = row.createCell(7);
        cel0.setCellValue(fmoney+"");
        cel0.setCellStyle(style);

        cel0 = row.createCell(8);
        cel0.setCellValue(soutmoney+"");
        cel0.setCellStyle(style);

        cel0 = row.createCell(9);
        cel0.setCellValue(sInmoney+"");
        cel0.setCellStyle(style);

        cel0 = row.createCell(10);
        cel0.setCellValue(inPriceTotal+"");
        cel0.setCellStyle(style);

        cel0 = row.createCell(11);
        cel0.setCellValue(profitTotal+"");
        cel0.setCellStyle(style);
    }

    public static void createFinanceContext(XSSFWorkbook workbook, XSSFSheet sheet, List<OrderTrade> orderList) {

        long stotal=0;
        BigDecimal smoney=new BigDecimal(0);
        BigDecimal soutmoney=new BigDecimal(0);
        BigDecimal sInmoney=new BigDecimal(0);
        BigDecimal succOutMoney=new BigDecimal(0);
        BigDecimal profitTotal = new BigDecimal(0);
        BigDecimal inPriceTotal = new BigDecimal(0);
        for(int i=1;i<orderList.size()+1;i++)
        {
            OrderTrade order = orderList.get(i-1);
            XSSFRow row = sheet.createRow(i);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(order.getAgtPhone());
            cell = row.createCell(1);

            cell.setCellValue(order.getAgtName());
            cell = row.createCell(2);

            cell.setCellValue(order.getSuccTotal());
            cell = row.createCell(3);

            cell.setCellValue(order.getSuccTotalPrice()+"");
            cell = row.createCell(4);

            cell.setCellValue(order.getSucctotalOutMoney()+"");
            cell = row.createCell(5);

            cell.setCellValue(order.getProductProfite()+"");
            cell = row.createCell(6);

            cell.setCellValue(order.getSucctotalInMoney()+"");
            cell = row.createCell(7);

            cell.setCellValue(order.getProfit()+"");
            cell = row.createCell(8);

            cell.setCellValue(order.getChannelName()+"");
            cell = row.createCell(9);

            stotal+=order.getSuccTotal();
            smoney=smoney.add(order.getSuccTotalPrice());
            sInmoney=sInmoney.add(order.getSucctotalInMoney());
            soutmoney=soutmoney.add(order.getSucctotalOutMoney());
            succOutMoney=succOutMoney.add(order.getSucctotalOutMoney());
            profitTotal=profitTotal.add(order.getProfit());
            inPriceTotal=inPriceTotal.add(order.getSuccTotalPrice());
        }
        XSSFRow row = sheet.createRow(orderList.size()+2);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);//设置字体大小
        style.setFont(font);

        XSSFCell cel0 = row.createCell(0);
        cel0.setCellValue("总计");
        cel0.setCellStyle(style);

        cel0 = row.createCell(2);
        cel0.setCellValue(stotal);
        cel0.setCellStyle(style);

        cel0 = row.createCell(3);
        cel0.setCellValue(smoney+"");
        cel0.setCellStyle(style);

        cel0 = row.createCell(4);
        cel0.setCellValue(soutmoney+"");
        cel0.setCellStyle(style);

        cel0 = row.createCell(6);
        cel0.setCellValue(sInmoney+"");
        cel0.setCellStyle(style);

        cel0 = row.createCell(7);
        cel0.setCellValue(profitTotal+"");
        cel0.setCellStyle(style);

    }
}
