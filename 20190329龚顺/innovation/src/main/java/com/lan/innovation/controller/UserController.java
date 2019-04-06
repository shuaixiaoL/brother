package com.lan.innovation.controller;

import com.lan.common.vo.BaseResultVo;
import com.lan.innovation.pojo.User;
import com.lan.innovation.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户相关
 */
@Controller
@RequestMapping("/api/innovation")
@SessionAttributes
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "/user/numlevel", method = RequestMethod.GET)
    @ResponseBody
    public BaseResultVo selectUserByNum(
            @RequestParam String num,
            @RequestParam Integer level)  throws Exception {
        BaseResultVo brv = new BaseResultVo();
        if (StringUtils.isBlank(num)) {
            brv.setResultMsg("9999", "用户名不能为空");
            return brv;
        }
        brv = userService.selectUserByNum(num, level);
         return brv;
    }

    /**
     * 登陆操作
     */
    @RequestMapping(value = "/user/numpwd", method = RequestMethod.GET)
    @ResponseBody
    public BaseResultVo login(User user, HttpServletRequest request) throws Exception {
        BaseResultVo brv = userService.selectUser(user);
        //存入session
        request.getSession(true).setAttribute("user", brv.getData());
        return brv;
    }

    /**
     * 登陆操作
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public BaseResultVo login2(User user, HttpServletRequest request) throws Exception {
        BaseResultVo brv = userService.selectUser(user);
        //存入session
        request.getSession(true).setAttribute("user", brv.getData());
        return brv;
    }


    /**
     * 注销
     */
    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String login(HttpServletRequest request) throws Exception {
        request.getSession(true).removeAttribute("user");
        return "login";
    }

    /**
     * 查询用户(条件分页)
     */
    @GetMapping(value = "/user")
    @ResponseBody
    public Map getUsers(
            @RequestParam(value = "level", defaultValue = "0",  required = false) Integer level,
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage,
            @RequestParam(value = "currentSize", defaultValue = "6", required = false) Integer currentSize
    ){
        return userService.selectUsers(level, currentPage, currentSize);
    }

}
