package com.lan.innovation.dao;

import com.lan.innovation.pojo.Params;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lan_jiaxing on 2018/4/13 0013.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class ParamsMapperTest {

    @Resource
    private ParamsMapper paramsMapper;

    @Test
    public void selectGroupByName() throws Exception {
        List<Params> paramsList = paramsMapper.selectGroupByName();
        for (Params params : paramsList) {
            System.out.println(params.getCnName());
        }
    }

}