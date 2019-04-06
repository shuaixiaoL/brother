package com.lan.innovation.service;

import com.lan.innovation.pojo.Params;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by lan_jiaxing on 2018/4/13 0013.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class ParamsServiceTest {

//    @Resource
//    private ParamsService paramsService;

    @Test
    public void initMethod() throws Exception {
        Map<String,List<Params>> CACHE = ParamsService.CACHE;
        for (String s : CACHE.keySet()) {
            System.out.println(s);
        }
    }

}