package com.lan.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 获得uuid
 */
public final class MyMD5 {

    public static String getMD5(String str) {
        String reStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte ss[] = md.digest();
            reStr = bytes2String(ss);
        } catch (NoSuchAlgorithmException e) {

        }
        return reStr;
    }

    public static String getSHA(String str) {
        String reStr = null;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            sha.update(str.getBytes());
            byte ss[] = sha.digest();
            reStr = bytes2String(ss);
        } catch (NoSuchAlgorithmException e) {

        }
        return reStr;

    }
    private static String bytes2String(byte[] aa) {
        String hash = "";
        for (int i = 0; i < aa.length; i++) {
            int temp;
            if (aa[i] < 0)
                temp = 256 + aa[i];
            else
                temp = aa[i];
            if (temp < 16)
                hash += "0";
            hash += Integer.toString(temp, 16);
        }
        hash = hash.toUpperCase();
        return hash;
    }

    public static void main(String[] args) {
        //测试md5
        String pwd = "123456789";
        String test1 = MyMD5.getMD5(pwd);
        System.out.println(test1);
    }
}
