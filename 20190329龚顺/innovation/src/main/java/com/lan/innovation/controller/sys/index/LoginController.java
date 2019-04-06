package com.lan.innovation.controller.sys.index;

import com.lan.common.vo.BaseResultVo;
import com.lan.common.vo.SysUserVo;
import com.lan.innovation.pojo.SysRole;
import com.lan.innovation.pojo.SysUser;
import com.lan.innovation.pojo.SysUserRole;
import com.lan.innovation.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private SysUserService userService;

    @RequestMapping("/login2")
    @ResponseBody
    public BaseResultVo login(SysUserVo sysUserVo, HttpSession session) {
        //登录
        BaseResultVo brv = new BaseResultVo();
        //验证账号密码
        List<SysUser> sysUsers = userService.checkUserPwd(sysUserVo);
        if(sysUsers != null &&sysUsers.size() == 0) {
            brv.setResultMsg("9999", "该账号不存在");
            return brv;
        }
        //验证权限
        SysRole sysRole = userService.checkRoleId(sysUserVo);
        if(sysRole == null) {
            brv.setResultMsg("9999", "该角色不存在");
            return brv;
        }
        //验证中间表
        sysUserVo.setId(sysUsers.get(0).getId());
        List<SysUserRole> sysUserRoles = userService.checkUserRole(sysUserVo);
        if(sysUserRoles != null &&sysUserRoles.size() == 0) {
            brv.setResultMsg("9999", "该账户不是此种角色");
            return brv;
        }

        session.setAttribute("sysUser", sysUsers.get(0));
        session.setAttribute("sysRole", sysRole);
        return brv;
    }

    /**
     * 注销
     */
    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String login(HttpSession session) {
        session.removeAttribute("sysUser");
        session.removeAttribute("sysRole");
        return "login";
    }
}
