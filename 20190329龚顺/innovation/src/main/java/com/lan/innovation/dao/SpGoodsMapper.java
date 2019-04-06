package com.lan.innovation.dao;

import com.lan.innovation.pojo.SpGoods;
import com.lan.innovation.pojo.SpGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpGoodsMapper {
    int countByExample(SpGoodsExample example);

    int deleteByExample(SpGoodsExample example);

    int deleteByPrimaryKey(String id);

    int insert(SpGoods record);

    int insertSelective(SpGoods record);

    List<SpGoods> selectByExample(SpGoodsExample example);

    SpGoods selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SpGoods record, @Param("example") SpGoodsExample example);

    int updateByExample(@Param("record") SpGoods record, @Param("example") SpGoodsExample example);

    int updateByPrimaryKeySelective(SpGoods record);

    int updateByPrimaryKey(SpGoods record);
}