package com.ikoori.vip.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.ikoori.vip.mobile.config.DubboConsumer;
import com.ikoori.vip.mobile.util.WeChatAPI;

/**
 * 优惠券领取
 * 
 * @ClassName: CouponController
 * @author: chengxg
 * @date: 2017年9月14日 上午11:07:51
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DubboConsumer consumer;

	/**
	 * 跳转优惠券领取页面
	 * 
	 * @Title: tofetch
	 * @param request
	 * @param map
	 * @return
	 * @date: 2017年9月14日 上午11:43:49
	 * @author: chengxg
	 * @throws Exception
	 */
	@RequestMapping(value = "/tofetch/{alias}", method = { RequestMethod.GET, RequestMethod.POST })
	public String tofetch(HttpServletRequest request, @PathVariable String alias,Model model) throws Exception {
		try {
			String openId = WeChatAPI.getOpenId(request.getSession());
			if (openId == null) {
				throw new Exception("登录信息有误");
			}
			Object obj = consumer.getCouponApi().get().getCouponByAlias(alias);
			model.addAttribute("coupon",obj);
			return "/coupon_fetch.html";
		} catch (Exception e) {
			log.error("跳转优惠券领取页面失败", e);
			throw e;
		}
	}

	/**
	 * 领取优惠券
	 * 
	 * @Title: fetch
	 * @param request
	 * @param map
	 * @return
	 * @date: 2017年9月14日 上午11:43:49
	 * @author: chengxg
	 * @throws Exception
	 */
	@RequestMapping(value = "/fetch/{alias}", method = { RequestMethod.GET, RequestMethod.POST })
	public String fetch(HttpServletRequest request, @PathVariable String alias, Model model) throws Exception {
		try {
			String openId = WeChatAPI.getOpenId(request.getSession());
			if (openId == null) {
				throw new Exception("登录信息有误");
			}
			consumer.getCouponApi().get().getCoupon(alias, openId);
			model.addAttribute("code",true);
			model.addAttribute("msg","该券已经放入您的账户");
		} catch (Exception e) {
			log.error("领取优惠券失败", e);
			model.addAttribute("code",false);
			// 判断是否为业务异常
			if (StringUtils.isNotBlank(e.getMessage()) && e.getMessage().matches("\\{(.*)\\}")) {
				model.addAttribute("msg", JSONObject.parseObject(e.getMessage()).get("msg"));
			}else{
				model.addAttribute("msg","发生未知错误！");
			}
		}
		// 跳转失败页面
		return "/coupon_fetch_result.html";
	}
}