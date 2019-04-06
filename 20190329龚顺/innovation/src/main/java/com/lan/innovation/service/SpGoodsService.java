package com.lan.innovation.service;

import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.GoodsVo;
import com.lan.innovation.pojo.SpGoods;
import com.lan.innovation.pojo.SysUser;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface SpGoodsService {

    GoodsVo queryGoods(GoodsVo goodsVo);

    GoodsVo queryGoodsByUserId(GoodsVo goodsVo, String userId);

    BaseResultVo deleteGoods(String id);

    BaseResultVo deleteBatchGoods(String ids);

    BaseResultVo updateGoods(SpGoods spGoods, String userId);

    BaseResultVo addGoods(SpGoods spGoods, String userId);

}
