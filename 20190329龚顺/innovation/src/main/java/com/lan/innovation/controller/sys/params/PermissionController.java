package com.lan.innovation.controller.sys.params;

import com.lan.innovation.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@Controller
public class PermissionController {

    @Autowired
    private SysPermissionService permissionService;

    @ResponseBody
    @RequestMapping("/getAllPermission")
    public List<Map<String, Object>> getAllPermission(){
        return permissionService.getAllPermission();
    }


}
