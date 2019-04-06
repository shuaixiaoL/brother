package com.lan.innovation.dao;

import com.lan.innovation.pojo.SpMessage;
import com.lan.innovation.pojo.SpMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpMessageMapper {
    int countByExample(SpMessageExample example);

    int deleteByExample(SpMessageExample example);

    int deleteByPrimaryKey(String id);

    int insert(SpMessage record);

    int insertSelective(SpMessage record);

    List<SpMessage> selectByExample(SpMessageExample example);

    SpMessage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SpMessage record, @Param("example") SpMessageExample example);

    int updateByExample(@Param("record") SpMessage record, @Param("example") SpMessageExample example);

    int updateByPrimaryKeySelective(SpMessage record);

    int updateByPrimaryKey(SpMessage record);
}