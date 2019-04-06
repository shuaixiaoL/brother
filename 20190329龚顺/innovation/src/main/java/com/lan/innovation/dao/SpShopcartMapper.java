package com.lan.innovation.dao;

import com.lan.innovation.pojo.SpShopcart;
import com.lan.innovation.pojo.SpShopcartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpShopcartMapper {
    int countByExample(SpShopcartExample example);

    int deleteByExample(SpShopcartExample example);

    int deleteByPrimaryKey(String id);

    int insert(SpShopcart record);

    int insertSelective(SpShopcart record);

    List<SpShopcart> selectByExample(SpShopcartExample example);

    SpShopcart selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SpShopcart record, @Param("example") SpShopcartExample example);

    int updateByExample(@Param("record") SpShopcart record, @Param("example") SpShopcartExample example);

    int updateByPrimaryKeySelective(SpShopcart record);

    int updateByPrimaryKey(SpShopcart record);
}