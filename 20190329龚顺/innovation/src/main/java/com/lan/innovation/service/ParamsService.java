package com.lan.innovation.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lan.innovation.dao.ParamsDao;
import com.lan.innovation.dao.ParamsMapper;
import com.lan.innovation.pojo.Params;
import com.lan.innovation.pojo.ParamsExample;
import com.lan.innovation.pojo.User;
import com.lan.innovation.pojo.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParamsService {

    @Resource
    private ParamsMapper paramsMapper;

    @Resource
    private ParamsDao paramsDao;

    public static Map<String,List<Params>> CACHE = new HashMap<String, List<Params>>();

    public void initMethod() {
        if (CACHE == null || CACHE.size() == 0) {
            //首先获得不可重复的 type,name,cn_name,这里只要name就行
            List<Params> params1 = paramsMapper.selectGroupByName();

            if (params1 != null && params1.size() > 0) {
                for (Params param : params1) {
                    ParamsExample pe1 = new ParamsExample();
                    ParamsExample.Criteria criteria = pe1.createCriteria();
                    criteria.andNameEqualTo(param.getName());
                    List<Params> params2 = paramsMapper.selectByExample(pe1);
                    CACHE.put(param.getName(), params2);
                }
            }
        }
    }

    /**
     * 查询所有配置
     */
    public List<Params> selectParams() {
        ParamsExample example1 = new ParamsExample();
        List<Params> params = paramsMapper.selectByExample(example1);
        return params;
    }


    public Map selectParamsOfFirst(Integer currentPage, Integer currentSize) {
        Map map = new HashMap();
        //1.设置分页信息
        PageHelper.startPage(currentPage, currentSize);
        //2.执行查询
        List<Params> params = paramsDao.selectGroupByType();
        //3.取分页结果 Rows：itemList , Total
        PageInfo<Params> pageInfo = new PageInfo<Params>(params, currentSize);
        map.put("paramsPageInfo", pageInfo);
        return map;
    }


    public Map selectParamsSecundByFirst(Params params, Integer currentPage, Integer currentSize) {
        Map map = new HashMap();
        //1.设置分页信息
        PageHelper.startPage(currentPage, currentSize);
        //2.执行查询
        ParamsExample example1 = new ParamsExample();
        ParamsExample.Criteria criteria = example1.createCriteria();
        criteria.andNameEqualTo(params.getName());
        List<Params> paramsSocund = paramsMapper.selectByExample(example1);
        //3.取分页结果 Rows：itemList , Total
        PageInfo<Params> pageInfo = new PageInfo<Params>(paramsSocund, currentSize);
        map.put("paramsSocundPageInfo", pageInfo);
        return map;
    }
}
