package com.ikoori.vip.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ikoori.vip.mobile.config.DubboConsumer;

@Controller
@RequestMapping("/member")
public class MemberController {
	//@Reference(version = "1.0.0")
	//private MemberService memberService;
	 @Autowired
	 DubboConsumer consumer;


	@RequestMapping("/login")
	@ResponseBody
	public String login() {
		//memberService.test();
		JSONObject obj = consumer.personConsumer().get().test("成");
		return obj.getString("name");
	}
}
