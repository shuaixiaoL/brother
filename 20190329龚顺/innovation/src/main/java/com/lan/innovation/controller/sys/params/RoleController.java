package com.lan.innovation.controller.sys.params;

import com.lan.common.utils.DtfpUtils;
import com.lan.common.vo.RoleVo;
import com.lan.innovation.pojo.SysRole;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@Controller
public class RoleController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ResponseBody
    @RequestMapping("/role")
    public List<Map<String, Object>> getRoleDim(HttpSession session){
        SysRole sysRole = (SysRole)session.getAttribute("sysRole");
        List<Map<String, Object>> list = new ArrayList<>();

        String sql = " SELECT * FROM sys_role where type = 'shop' ";
        List<Map<String, Object>> lev1s = jdbcTemplate.queryForList(sql);

        for (int j = 0; j < lev1s.size(); j++) {
            Map<String, Object> mapj = new HashMap<>();
            mapj.put("text", lev1s.get(j).get("role_name"));
            mapj.put("value", lev1s.get(j).get("role_code"));
            list.add(mapj);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("/roleByLevel")
    public List<Map<String, Object>> getRoleByLevel(HttpSession session){
        SysRole sysRole = (SysRole)session.getAttribute("sysRole");
        List<Map<String, Object>> list = new ArrayList<>();

        String sql = " SELECT * FROM sys_role where type = 'shop' AND role_level >= '"+ sysRole.getRoleLevel() +"'";
        List<Map<String, Object>> lev1s = jdbcTemplate.queryForList(sql);

        for (int j = 0; j < lev1s.size(); j++) {
            Map<String, Object> mapj = new HashMap<>();
            mapj.put("text", lev1s.get(j).get("role_name"));
            mapj.put("value", lev1s.get(j).get("role_code"));
            list.add(mapj);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("/getRole")
    public List<Map<String, Object>> getRole(HttpSession session){
        String sql = " SELECT * FROM sys_role where type = 'shop' ";
        return DtfpUtils.changeMapKeys(jdbcTemplate.queryForList(sql));
    }


    @ResponseBody
    @RequestMapping("/getRolePermission")
    public List<Long> getRolePermission(RoleVo roleVo){
        List<Long> result = new ArrayList<>();

        String sql = " SELECT srp.* FROM sys_role_permission srp INNER JOIN sys_permission sp ON sp.id = srp.sys_permission_id where sys_role_id = '"+ roleVo.getId() +"' AND percode = 2 ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (int i = 0; i < list.size(); i++) {
            result.add(Long.parseLong(list.get(i).get("sys_permission_id").toString()));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/applePermission")
    public void applePermission(RoleVo roleVo){
        List<Long> result = new ArrayList<>();

        String sql = " DELETE FROM sys_role_permission where sys_role_id = '"+ roleVo.getId() +"' ";
        jdbcTemplate.execute(sql);

        String sql2 = " INSERT INTO sys_role_permission ( id, sys_role_id, sys_permission_id ) VALUES ( ?, ?, ? ) ";

        JSONArray jsonArray = JSONArray.fromObject(roleVo.getPermissionIds());
        List<String> permissionIds = new ArrayList<>();
        if(jsonArray.size() > 0) {
            for (Object o : jsonArray) {
                permissionIds.add(o + "");
            }
        }

        List<Object []> batchArgs = new ArrayList<>();
        for (int i = 0; i < permissionIds.size(); i++) {
            batchArgs.add(new Object[]{
               DtfpUtils.getUUID(),
               roleVo.getId(),
               permissionIds.get(i) + ""
            });
        }
        jdbcTemplate.batchUpdate(sql2, batchArgs);
    }
}
