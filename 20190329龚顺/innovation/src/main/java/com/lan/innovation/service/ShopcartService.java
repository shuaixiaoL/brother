package com.lan.innovation.service;

import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.GoodsVo;
import com.lan.common.vo.ShopcartVo;

public interface ShopcartService {

    BaseResultVo addShopcart(GoodsVo goodsVo, String userId);

    ShopcartVo queryShopcartByUserId(ShopcartVo shopcartVo, String userId);

    BaseResultVo deleteShopcart(String id);

    BaseResultVo deleteBatchShopcart(String ids);

    BaseResultVo pay(ShopcartVo shopcartVo, String id) throws Exception;

}


