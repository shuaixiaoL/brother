package com.lan.innovation.controller.index;

import com.lan.common.vo.BaseResultVo;
import com.lan.innovation.pojo.SysRole;
import com.lan.innovation.pojo.SysUser;
import com.lan.innovation.pojo.SysUserRole;
import com.lan.common.vo.SysUserVo;
import com.lan.innovation.service.ParamsService;
import com.lan.innovation.service.PermissionService;
import com.lan.innovation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserRoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private ParamsService paramsService;

    @Autowired
    private PermissionService permissionService;

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
            brv.setResultMsg("9999", "中间关系不存在");
            return brv;
        }

        session.setAttribute("sysUser", sysUsers.get(0));
        session.setAttribute("sysRole", sysRole);
        return brv;
    }

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

    @ResponseBody
    @RequestMapping("/getPermission")
    public List<Map<String, Object>> getPermission(HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        SysRole sysRole = (SysRole)session.getAttribute("sysRole");

        return permissionService.getPermission(sysRole);

    }
}
