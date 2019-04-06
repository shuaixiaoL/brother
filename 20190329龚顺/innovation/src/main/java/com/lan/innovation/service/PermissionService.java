package com.lan.innovation.service;

import com.lan.innovation.pojo.SysRole;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    public List<Map<String, Object>> getPermission(SysRole sysRole);

    public List<Map<String, Object>> getAllPermission();
}
