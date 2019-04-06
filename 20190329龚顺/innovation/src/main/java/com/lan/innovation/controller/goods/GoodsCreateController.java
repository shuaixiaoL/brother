package com.lan.innovation.controller.goods;

import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.GoodsVo;
import com.lan.innovation.pojo.SpGoods;
import com.lan.innovation.pojo.SysUser;
import com.lan.innovation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/goodsCreate")
public class GoodsCreateController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/queryGoodsByUserId")
    @ResponseBody
    public GoodsVo queryGoods(GoodsVo goodsVo, HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        return goodsService.queryGoodsByUserId(goodsVo, sysUser.getId());
    }

    @RequestMapping("/deleteGoods/{id}")
    @ResponseBody
    public BaseResultVo deleteGoods(@PathVariable String id) {
        return goodsService.deleteGoods(id);
    }

    @RequestMapping("/deleteBatchGoods")
    @ResponseBody
    public BaseResultVo deleteBatchGoods(@RequestBody GoodsVo goodsVo) {
        return goodsService.deleteBatchGoods(goodsVo.getIds());
    }

    @RequestMapping("/updateGoods")
    @ResponseBody
    public BaseResultVo updateGoods(@RequestBody SpGoods spGoods, HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        return goodsService.updateGoods(spGoods, sysUser.getId());
    }

    @RequestMapping("/addGoods")
    @ResponseBody
    public BaseResultVo addGoods(@RequestBody SpGoods spGoods, HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        return goodsService.addGoods(spGoods, sysUser.getId());
    }
}
