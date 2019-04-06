package com.lan.innovation.dao;

import com.lan.innovation.pojo.Params;
import com.lan.innovation.pojo.ParamsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParamsMapper {
    int countByExample(ParamsExample example);

    int deleteByExample(ParamsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Params record);

    int insertSelective(Params record);

    List<Params> selectByExample(ParamsExample example);

    Params selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Params record, @Param("example") ParamsExample example);

    int updateByExample(@Param("record") Params record, @Param("example") ParamsExample example);

    int updateByPrimaryKeySelective(Params record);

    int updateByPrimaryKey(Params record);

    /*自定义 只要获得type name cn_name ,用对象接受，难得自定义类型接受 */
    List<Params> selectGroupByName();
}