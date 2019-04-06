package com.lan.innovation.controller;

import com.lan.innovation.pojo.Params;
import com.lan.innovation.service.ParamsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户相关
 */
@Controller
@RequestMapping("/api/innovation")
public class ParamsController {

    private static final Logger LOG = LoggerFactory.getLogger(ParamsController.class);

    @Resource
    private ParamsService paramsService;


    /**
     * 查询配置（所有）
     */
    @GetMapping(value = "/params")
    @ResponseBody
    public List<Params> selectParams(){
        return paramsService.selectParams();
    }

    /**
     * 查询配置（一级所有所有）
     */
    @GetMapping(value = "/params/first")
    @ResponseBody
    public Map selectParams(
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage,
            @RequestParam(value = "currentSize", defaultValue = "6", required = false) Integer currentSize
    ){
        return paramsService.selectParamsOfFirst(currentPage, currentSize);
    }

    /**
     * 查询配置（一级所有所有）
     */
    @GetMapping(value = "/params/secundfirst")
    @ResponseBody
    public Map selectParamsSecundByFirst(
            Params params,
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage,
            @RequestParam(value = "currentSize", defaultValue = "6", required = false) Integer currentSize
    ){
        return paramsService.selectParamsSecundByFirst(params, currentPage, currentSize);
    }

}
