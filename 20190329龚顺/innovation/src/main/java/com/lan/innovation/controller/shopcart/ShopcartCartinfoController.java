package com.lan.innovation.controller.shopcart;

import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.GoodsVo;
import com.lan.common.vo.ShopcartVo;
import com.lan.innovation.pojo.SysUser;
import com.lan.innovation.service.ShopcartService;
import com.lan.innovation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/shopcartCartinfo")
public class ShopcartCartinfoController {

    @Autowired
    private ShopcartService shopcartService;

    @Autowired
    private UserService userService;

    @RequestMapping("/queryShopcartByUserId")
    @ResponseBody
    public ShopcartVo queryShopcartByUserId(ShopcartVo shopcartVo, HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        return shopcartService.queryShopcartByUserId(shopcartVo, sysUser.getId());
    }

    @RequestMapping("/getMoneyByUserId")
    @ResponseBody
    public BaseResultVo getMoneyByUserId(HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        return userService.getMoneyByUserId(sysUser.getId());
    }

    @RequestMapping("/deleteShopcart/{id}")
    @ResponseBody
    public BaseResultVo deleteShopcart(@PathVariable String id) {
        return shopcartService.deleteShopcart(id);
    }

    @RequestMapping("/deleteBatchShopcart")
    @ResponseBody
    public BaseResultVo deleteBatchShopcart(@RequestBody ShopcartVo shopcartVo) {
        return shopcartService.deleteBatchShopcart(shopcartVo.getIds());
    }

    @RequestMapping("/pay")
    @ResponseBody
    public BaseResultVo pay(@RequestBody ShopcartVo shopcartVo, HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        BaseResultVo brv = new BaseResultVo();
        try {
            brv = shopcartService.pay(shopcartVo, sysUser.getId());
            return brv;
        } catch (Exception e) {
            e.printStackTrace();
            return brv;
        }
    }
}
