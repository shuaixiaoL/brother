package com.lan.innovation.controller.shop.goods;

import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.GoodsVo;
import com.lan.innovation.pojo.SysUser;
import com.lan.innovation.service.SpGoodsService;
import com.lan.innovation.service.SpShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/goodsIndex")
public class GoodsIndexController {

    @Autowired
    private SpGoodsService goodsService;

    @Autowired
    private SpShopcartService shopcartService;

    @RequestMapping("/queryGoods")
    @ResponseBody
    public GoodsVo queryGoods(@RequestBody GoodsVo goodsVo) {
        return goodsService.queryGoods(goodsVo);
    }

    @RequestMapping("/addShopcart")
    @ResponseBody
    public BaseResultVo addShopcart(@RequestBody GoodsVo goodsVo, HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        return shopcartService.addShopcart(goodsVo, sysUser.getId());
    }

}
