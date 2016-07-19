package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.weixinUtil.CheckUtil;
import com.weixinUtil.MessageUtil;

@Controller
public class MobController {
	@RequestMapping(value = "weixin/getWeixinServer", method = RequestMethod.GET)
	public void getWeixinServer(String signature, String timestamp, String nonce, String echostr,
			HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			if (CheckUtil.checkSignature(signature, timestamp, nonce))
				out.println(echostr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "weixin/getWeixinServer", method = RequestMethod.POST)
	public void Message(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Map map = MessageUtil.xmlToMap(request);
		String fromUserName = (String) map.get("FromUserName");
		String toUserName = (String) map.get("ToUserName");
		String msgType = (String) map.get("MsgType");
		String content = (String) map.get("Content");
		String message = null;
		if ("text".equals(msgType)) {
			if ("1".equals(content)) {
				message = MessageUtil.initNewsMessage(toUserName, fromUserName, content);
			}else if ("2".equals(content)) {
				message = MessageUtil.initMusicMessage(toUserName, fromUserName, content);
			}
		} else if ("event".equals(msgType)) {
			String eventTpye = (String)map.get("Event");
			if ("subscribe".equals(eventTpye)) {
				message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
			}
		}
		out.print(message);
	}

	@RequestMapping("weixin/newMessage")
	public ModelAndView toNewMessage() {
		return new ModelAndView("/music");
	}

	@RequestMapping("weixin/newMusic")
	public ModelAndView toNewMusic() {
		return new ModelAndView("/listenMusic");
	}
}