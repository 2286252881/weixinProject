package com.weixinUtil.test;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;

import com.alibaba.fastjson.JSONObject;
import com.weixin.po.AccessToken;
import com.weixinUtil.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) throws ParseException, IOException {
		AccessToken token = WeixinUtil.getAccessToken();
		System.out.println("票据:" + token.getToken());
		System.out.println("有效时间:" + token.getExpiresIn());
		
		//获取服务器IP
		/*List<String> ip_list=WeixinUtil.getCallback_IP();
		for (String ip : ip_list) {
			System.out.println(ip);
		}*/
		
		
		//删除微信菜单
		WeixinUtil.deleteMenu(token.getToken());
		//创建微信菜单
		int result=1;
		String menu=JSONObject.toJSON(WeixinUtil.initMenu()).toString();
		try {
			result=WeixinUtil.createMenu(token.getToken(),menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result==0){
			System.out.println("创建菜单成功!");
		}else{
			System.out.println("错误码:"+result);
		}
	}
}
