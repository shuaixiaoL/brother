package com.lan.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public final class CreateUuid {
    /**
     * 最常规的
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 以下是本系统自定义的
     */
    /**
     * 项目的uuid编号
     */
    public static String getProjectTitleUUID() {
        Date nowDate = new Date();
        SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");
        String date = time.format(nowDate).toString();
        return "TI" + date + getUUID().substring(0,5);
    }

    /**
     * 中期的uuid编号
     */
    public static String getProjectMidUUID() {
        Date nowDate = new Date();
        SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");
        String date = time.format(nowDate).toString();
        return "MI" + date + getUUID().substring(0,5);
    }

    /**
     * 成果的uuid编号
     */
    public static String getProjectResultUUID() {
        Date nowDate = new Date();
        SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");
        String date = time.format(nowDate).toString();
        return "RI" + date + getUUID().substring(0,5);
    }

    /**
     * 成果的uuid编号
     */
    public static String getProjectWorkUUID() {
        Date nowDate = new Date();
        SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");
        String date = time.format(nowDate).toString();
        return "WI" + date + getUUID().substring(0,5);
    }

    public static void main(String[] args) {
        String test1 = CreateUuid.getUUID();
        System.out.println(test1);

    }
}
