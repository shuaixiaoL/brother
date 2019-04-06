package com.lan.innovation.controller.sys.params;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@Controller
public class DimController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ResponseBody
    @RequestMapping("/dim/{objval}")
    public Map<String, Object> getDim(@PathVariable String objval){
        Map<String, Object> map = new HashMap<>();

        String [] objvals = objval.split(",");
        for (int i = 0; i < objvals.length; i++) {
            String sql = " SELECT * FROM fw_dim where pid = (SELECT dim_id  FROM fw_dim where dim_code = '"+ objvals[i] +"') ";
            List<Map<String, Object>>  lev1s = jdbcTemplate.queryForList(sql);
            List<Map<String, Object>> list = new ArrayList<>();
            for (int j = 0; j < lev1s.size(); j++) {
                Map<String, Object> mapj = new HashMap<>();
                mapj.put("text", lev1s.get(j).get("dim_name"));
                mapj.put("value", lev1s.get(j).get("dim_code"));
                list.add(mapj);
            }
            map.put(objvals[i], list);
        }
        return map;
    }

}


