package com.lan.innovation.controller.sys.index;

import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.SysUserVo;
import com.lan.innovation.pojo.SysUser;
import com.lan.innovation.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistComtroller {

    @Autowired
    private SysUserService userService;

    @RequestMapping("/regist2")
    @ResponseBody
    public BaseResultVo regist(SysUserVo sysUserVo) {
        //注册为普通用户
        SysUser sysUser = new SysUser();
        sysUser.setUsercode(sysUserVo.getUsercode());
        sysUser.setUsername(sysUserVo.getUsername());
        sysUser.setPassword(sysUserVo.getPassword());
        BaseResultVo brv = new BaseResultVo();
        try {
            brv = userService.regist(sysUser);
            return brv;
        } catch (Exception e) {
            e.printStackTrace();
            brv.setResultMsg("9999", e.getMessage());
            return brv;
        }
    }
}
