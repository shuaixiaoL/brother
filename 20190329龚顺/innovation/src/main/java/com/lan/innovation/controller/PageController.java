package com.lan.innovation.controller;

import com.lan.common.resultvo.ActiveUser;
import com.lan.innovation.exception.CustomException;
import com.lan.innovation.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class PageController {

    //访问首页(暂未使用)
    @RequestMapping("/")
    public String showIndex() {
        return "login";
    }

//    @RequestMapping("/index")
//    public String index(Model model) {
//        return "/index";
//    }
//
//    @RequestMapping("/login")
//    public String login(Model model) {
//        return "/login";
//    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) throws Exception {
        return page;
    }

    @RequestMapping("/{page}/{page2}")
    public String showPage(@PathVariable String page,
                            @PathVariable String page2) {
        return page + "/" + page2;
    }

    @RequestMapping("/{page}/{page2}/{page3}")
    public String showPage(@PathVariable String page,
                            @PathVariable String page2,
                            @PathVariable String page3) {
        return page + "/" + page2 + "/" + page3;
    }

    /**
     * 登陆操作
     */
//    @RequestMapping(value = "/loginSubmit", method = RequestMethod.GET)
//    public String login(User user, HttpServletRequest request, Model model) throws Exception {
////         shiro在认证过程中出现错误后将异常类路径通过request返回
//        //如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
//        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
//        //根据shiro返回的异常类路径判断，抛出指定异常信息
//        if(exceptionClassName != null){
//            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
//                //最终会抛给异常处理器
//                throw new CustomException("账号不存在");
//            } else if (IncorrectCredentialsException.class.getName().equals(
//                    exceptionClassName)) {
//                throw new CustomException("用户名/密码错误");
//            } else if("randomCodeError".equals(exceptionClassName)){
//                throw new CustomException("验证码错误 ");
//            }else {
//                throw new Exception();//最终在异常处理器生成未知错误
//            }
//        }
//        UsernamePasswordToken token = new UsernamePasswordToken(user.getNum(), user.getPassword());
//        //主体
//        Subject subject = SecurityUtils.getSubject();
//        //身份
//        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
//        model.addAttribute("activeUser", activeUser);
//        return "login";
//    }

}

