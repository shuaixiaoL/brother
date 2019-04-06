package com.lan.innovation.controller.sys.index;

import com.lan.innovation.pojo.SysRole;
import com.lan.innovation.pojo.SysUser;
import com.lan.innovation.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private SysPermissionService permissionService;


    @ResponseBody
    @RequestMapping("/getPermission")
    public List<Map<String, Object>> getPermission(HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        SysRole sysRole = (SysRole)session.getAttribute("sysRole");

        return permissionService.getPermission(sysRole);

    }
}
