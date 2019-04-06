package com.lan.innovation.controller.shop.info;

import com.lan.common.vo.BaseResultVo;
import com.lan.innovation.pojo.SysRole;
import com.lan.innovation.pojo.SysUser;
import com.lan.common.vo.SysUserVo;
import com.lan.innovation.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/api/info")
@Controller
public class UserInfoController {

    @Autowired
    private SysUserService userService;

    @ResponseBody
    @RequestMapping("/queryUsersInfoByExRole")
    public SysUserVo getUsersInfoByRole(SysUserVo sysUserVo, HttpSession session){
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        SysRole sysRole = (SysRole)session.getAttribute("sysRole");

        return userService.queryUsersInfoByExRole(sysUserVo, sysRole);
    }

    @ResponseBody
    @RequestMapping("/updateRoleId")
    public BaseResultVo updateRoleId(SysUserVo sysUserVo){
        return userService.updateRoleId(sysUserVo);
    }

    @ResponseBody
    @RequestMapping("/deleteUser")
    public BaseResultVo deleteUser(SysUserVo sysUserVo){
        return userService.deleteUser(sysUserVo);
    }

    @GetMapping(value = "/getUserById")
    @ResponseBody
    public SysUser getUserById(HttpSession session){
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        return userService.getUserById(sysUser);
    }

    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public BaseResultVo updateUser(@RequestBody SysUser user, HttpSession session){
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        return userService.updateUser(user, sysUser.getId());
    }


}
