package com.lan.innovation.dao;

import com.lan.innovation.pojo.Params;
import com.lan.innovation.pojo.ParamsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParamsDao {

    /*自定义 只要获得type name cn_name ,用对象接受，难得自定义类型接受 */
    List<Params> selectGroupByType();
}