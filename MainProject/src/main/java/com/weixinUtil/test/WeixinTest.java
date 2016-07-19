package com.weixinUtil.test;

import java.io.IOException;

import org.apache.http.ParseException;

import com.weixin.po.AccessToken;
import com.weixinUtil.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) throws ParseException, IOException {
		AccessToken token = WeixinUtil.getAccessToken();
		System.out.println("票据:" + token.getToken());
		System.out.println("有效时间:" + token.getExpiresIn());
		
	}
}
