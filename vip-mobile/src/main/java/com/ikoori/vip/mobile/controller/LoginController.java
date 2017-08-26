package com.ikoori.vip.mobile.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/login")
public class LoginController 
{

	private static final boolean debug = false; // 开发模式
	
    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public String index(HttpServletRequest request, Map<String, Object> map)
    {
    	return "redirect:index";
    	//return "/index.html";
    }

}