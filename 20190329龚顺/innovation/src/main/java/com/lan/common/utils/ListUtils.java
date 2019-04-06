package com.lan.common.utils;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lan_jiaxing on 2018/4/17 0017.
 */
public class ListUtils {
    /**
     * 从list中随机抽取元素
     *
     * @param list
     * @param n
     * @return void
     * @throws
     * @Title: createRandomList
     * @Description: TODO
     */
    public static List createRandomList(List list, int n) {
        // TODO Auto-generated method stub
        Map map = new HashMap();
        List listNew = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random = (int) (Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    System.out.println(random + "===========" + list.get(random));
                    listNew.add(list.get(random));
                }
            }
            return listNew;
        }
    }

    public static void main(String[] args) {
        List<String> l = new ArrayList<String>();
        l.add("222");
        l.add("2221");
        l.add("2222");
        l.add("2523");
        l.add("2224");
        l.add("2225");
        l.add("2525");
        l.add("2225");
        l.add("25");
        l.add("2925");

        List<String> ll = createRandomList(l, 2);

        for (String s : ll) {
            System.out.println(s);
        }
    }
}
