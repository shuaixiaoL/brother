package com.lan.innovation.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


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

}

