package com.lfs.admin.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
/**
 * Copyright: Copyright (c) 2019  Dylan
 * @ClassName: LocalDateUtil
 * @Description: 基于JAVA8的LocalDate封装工具类,NOT SimpleDateFormat
 * @version: v1.0.0
 * @author: Dylan
 * @date  
 */
public class LocalDateUtil {

   static  final String dataTimeFormat ="yyyy-MM-dd HH:mm:ss";

    /**
     * 转换时间成string类型
     * @param time  时间戳
     * @return
     */
    public static String convertTimeToString(Long time){
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(dataTimeFormat);
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    /**
     * 将字符串转日期成Long类型的时间戳，格式为：yyyy-MM-dd HH:mm:ss
     */
    public static Long convertTimeToLong(String date) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(dataTimeFormat);
        LocalDateTime parse = LocalDateTime.parse(date, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 当前日期相加
     * @return
     */
    public  static  String plusSeconds(Long seconds){
        LocalDateTime localDateTime =  LocalDateTime.now();
        LocalDateTime newTime = localDateTime.plusSeconds(seconds);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dataTimeFormat);
        String str =  newTime.format(formatter);
        return str;
    }

}
