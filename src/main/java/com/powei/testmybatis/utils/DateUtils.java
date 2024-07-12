package com.powei.testmybatis.utils;

import java.sql.Date;

public class DateUtils {
    public static Date generateDate(Integer year, Integer month, Integer day){
        return new Date(year - 1900, month - 1, day);
    }
}
