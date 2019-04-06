package com.lan.common.utils;

import com.lan.innovation.pojo.Params;
import com.lan.innovation.service.ParamsService;

import java.util.List;
import java.util.Map;

/**
 * Created by lan_jiaxing on 2018/4/13 0013.
 */
public class ParamsUtils {

    private static Map<String,List<Params>> paramsCache = ParamsService.CACHE;

    //获得根据类型和名称获得对应的keyset和valueset,name为map的key
    public static String getValuesetByNameKey(String name, Integer keyset) {
        for (String s : paramsCache.keySet()) {
            if (name.equals(s)) {
                List<Params> paramsTemp = paramsCache.get(s);
                if (paramsTemp != null && paramsTemp.size() > 0) {
                    for (Params params : paramsTemp) {
                        if (keyset == params.getKeyset()) {
                            return params.getValueset();
                        }
                    }
                }
            }
        }
        return "";
    }

}
