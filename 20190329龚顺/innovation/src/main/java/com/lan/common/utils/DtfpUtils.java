package com.lan.common.utils;

import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DtfpUtils {

    /**
     * 返回去除"-" uuid
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 生产填报时间2
     */
    public static List<Map<String, Object>> createFillDate(int indexLength) {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = null;
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < indexLength; i++) {
            map = new HashMap<>();
            // 第一个是 增量(时间)
            c.add(Calendar.MONTH, -(indexLength - 1 - i));// 月份减1
            Date resultDate = c.getTime(); // 结果
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            c.setTime(new Date());
            map.put("fill_date", sdf.format(resultDate));

            list.add(map);
        }
        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }

    /**
     * 生产填报时间2
     */
    public static List<Map<String, Object>> createFillDate2(String end,int indexLength) {
        List<Map<String, Object>> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Map<String, Object> map = null;
        Calendar c = Calendar.getInstance();

        for (int i = 0; i < indexLength; i++) {
            map = new HashMap<>();
            try {
                c.setTime(sdf.parse(end));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 第一个是 增量(时间)
            c.add(Calendar.MONTH, -(indexLength - i));// 月份减1
            Date resultDate = c.getTime(); // 结果

            c.setTime(new Date());
            map.put("fill_date", sdf.format(resultDate));

            list.add(map);
        }
        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }



    /**
     * 4.产生数据整数 含头不含尾
     */
    public static int getIntRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }

    /**
     *  用于将改成驼峰式2
     * @param listMap
     * @return
     */
    public static List<Map<String, Object>> changeMapKeys(List<Map<String, Object>> listMap) {
        if(listMap.size() == 0) {
            return null;
        }
        String [] keys = new String[listMap.get(0).keySet().size()];
        String [] keysResult = new String[listMap.get(0).keySet().size()];
        List<String> list = new ArrayList<>(listMap.get(0).keySet());
        for (int i = 0; i < list.size(); i++) {
            keys[i] = list.get(i);
        }
        keysResult = Uppercase4FirstLetter.convertToJava(keys);

        List<Map<String, Object>> listMapResult = new ArrayList<>();
        Map<String, Object> mapResult = null;
        for (Map<String, Object> map : listMap) {
            mapResult = new HashMap<>();
            for (int i = 0; i < map.size(); i++) {
                mapResult.put(keysResult[i], map.get(keys[i]));//添加新key
            }
            listMapResult.add(mapResult);
        }
        listMap = null;
        return listMapResult;
    }

    //为空是代表不当搜索条件
    public static List<String> jsonArrToOrclInStr(String str) {
        if(StringUtils.isNotBlank(str)) {
            JSONArray jsonArray = JSONArray.fromObject(str);
            List<String> result = new ArrayList<>();
            if(jsonArray.size() > 0) {
                for (Object o : jsonArray) {
                    result.add((String) o);
                }
                return result;
            } else {
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }

    }

}
