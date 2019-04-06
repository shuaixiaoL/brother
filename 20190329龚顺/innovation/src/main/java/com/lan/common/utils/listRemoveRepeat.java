package com.lan.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * list去重
 */
public class listRemoveRepeat {

    //set集合去重，不打乱顺序
    public static void main(String[] args){
        List<String> list  =   new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        getNoRepeatListByList(list);

        List newList = getNoRepeatList(list);
        System.out.println( "去重后的集合： " + newList);
    }

    //2.遍历后判断赋给另一个list集合
    public static List<String> getNoRepeatListByList(List<String> list) {
        List<String> newList = new ArrayList<String>();
        for (String cd:list) {
            if(!newList.contains(cd)){
                newList.add(cd);
            }
        }
        return newList;
    }

    //1.set集合去重，不打乱顺序
    public static List getNoRepeatList(List<String> list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (String cd:list) {
            if(set.add(cd)){
                newList.add(cd);
            }
        }
        return newList;
    }

}
