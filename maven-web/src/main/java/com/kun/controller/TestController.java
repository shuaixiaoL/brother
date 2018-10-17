package com.kun.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kun.service.TestService;

@Controller
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value = "/hello")
	@ResponseBody
	public String scoreConfirm() {
		return testService.getString();
	}
}
