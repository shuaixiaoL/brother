package com.lan.innovation.service.impl;

import com.lan.innovation.pojo.SysRole;
import com.lan.innovation.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getPermission(SysRole sysRole) {
        if(null == sysRole) {
            return null;
        }

        List<Map<String, Object>>  list = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT sp.*  FROM sys_permission sp INNER JOIN sys_role_permission srp ON sp.id = srp.sys_permission_id where percode = '1' and  type = 'shop' ");
        sql.append(" AND  srp.sys_role_id = '" + sysRole.getId() + "'");
        List<Map<String, Object>>  lev1s = jdbcTemplate.queryForList(sql.toString());
        for (int i = 0; i < lev1s.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", lev1s.get(i).get("id"));
            map.put("text", lev1s.get(i).get("name"));
            map.put("icon", lev1s.get(i).get("icon"));
            map.put("url", lev1s.get(i).get("url"));
            String sql2 = " SELECT sp.*  FROM sys_permission sp INNER JOIN sys_role_permission srp ON sp.id = srp.sys_permission_id where parentid = " + lev1s.get(i).get("id") + " AND  srp.sys_role_id = '" + sysRole.getId() + "'";
            List<Map<String, Object>>  lev2s = jdbcTemplate.queryForList(sql2);
            List<Map<String, Object>>  list2 = new ArrayList<>();
            for (int j = 0; j < lev2s.size(); j++) {
                Map<String, Object> map2 = new HashMap<>();
                map2.put("id", lev2s.get(j).get("id"));
                map2.put("text", lev2s.get(j).get("name"));
                map2.put("icon", lev2s.get(j).get("icon"));
                map2.put("url", lev2s.get(j).get("url"));
                list2.add(map2);
            }
            map.put("menus", list2);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getAllPermission() {
        List<Map<String, Object>>  list = new ArrayList<>();
        List<Map<String, Object>>  lev1s = jdbcTemplate.queryForList(" SELECT sp.*  FROM sys_permission sp  where percode = '1' and  type = 'shop' ");
        for (int i = 0; i < lev1s.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", lev1s.get(i).get("id"));
            map.put("label", lev1s.get(i).get("name"));
            List<Map<String, Object>>  lev2s = jdbcTemplate.queryForList(" SELECT sp.*  FROM sys_permission sp where parentid = " + lev1s.get(i).get("id"));
            List<Map<String, Object>>  list2 = new ArrayList<>();
            for (int j = 0; j < lev2s.size(); j++) {
                Map<String, Object> map2 = new HashMap<>();
                map2.put("id", lev2s.get(j).get("id"));
                map2.put("label", lev2s.get(j).get("name"));
                list2.add(map2);
            }
            map.put("children", list2);
            list.add(map);
        }
        return list;
    }
}
