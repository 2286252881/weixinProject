package com.weixinUtil;

import com.thoughtworks.xstream.XStream;
import com.weixin.po.Image;
import com.weixin.po.ImageMessage;
import com.weixin.po.Music;
import com.weixin.po.MusicMessage;
import com.weixin.po.News;
import com.weixin.po.NewsMessage;
import com.weixin.po.TextMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "click";
	public static final String MESSAGE_VIEW = "view";
	public static final String MESSAGE_SCANCODE = "scancode_push";

	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for(Element e : list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}

	public static String textMessagetoXml(TextMessage textMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}

	public static String newsMessagetoXml(NewsMessage newsMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item", new News().getClass());
		return xStream.toXML(newsMessage);
	}

	public static String imageMessagetoXml(ImageMessage imageMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", imageMessage.getClass());
		return xStream.toXML(imageMessage);
	}

	public static String musicMessage(MusicMessage musicMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", musicMessage.getClass());
		return xStream.toXML(musicMessage);
	}

	public static String initNewsMessage(String toUserName, String fromUserName, String content) {
		String message = null;
		List newsList = new ArrayList();
		NewsMessage newsMessage = new NewsMessage();
		News news = new News();
		news.setTitle("今日推荐歌曲介绍");
		news.setDescription("《丑八怪》是薛之谦演唱的一首歌曲，由甘世佳填词，李荣浩谱曲，收录在薛之谦2013年发行的专辑《意外》中。2014年，该歌曲获MusicRadio中国TOP排行榜年度金曲奖。");
		news.setPicUrl("http://zhwx.ngrok.cc/wx/image/1.jpg");
		news.setUrl(WeixinUtil.getRequestCodeUrl("http://zhwx.ngrok.cc/wx/weixin/newMessage.action"));
		newsList.add(news);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType("news");
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		message = newsMessagetoXml(newsMessage);
		return message;
	}

	public static String initMusicMessage(String toUserName, String fromUserName, String content) {
		Music music = new Music();
		music.setThumbMediaId("qw6oowX5h6wCUneeg_xpPrPwSccLs4f5udbGNNRkbi_NxaEjoEUZzEm-zw2V5C5V");
		music.setTitle("see you again...");
		music.setDescription("速度与激情7");
		music.setMusicUrl("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb1db314939e2b8b2&secret=838ce2ad3bca756da91fe9a72a18e7b3");
		music.setHQMusicUrl(WeixinUtil.getRequestCodeUrl("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb1db314939e2b8b2&secret=838ce2ad3bca756da91fe9a72a18e7b3"));
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUserName);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setMsgType("music");
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMusic(music);
		return musicMessage(musicMessage);
	}

	public static String initImageMessage(String toUserName, String fromUserName, String content) {
		Image image = new Image();
		image.setMediaId("eINkYeuZTU2_H2zsWXPqwiI_lHNCeKbr1Ch-5wEIgQjdyndBvRFjvzn_d94mjJAv");
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType("image");
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		return imageMessagetoXml(imageMessage);
	}

	public static String initText(String toUserName, String fromUserName, String content) {
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType("text");
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessagetoXml(text);
	}

	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注,请按照菜单提示进行操作!\n\n");
		sb.append("1、今日歌曲\n");
		sb.append("2、music\n");
		return sb.toString();
	}

	public static String firstMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("推送音乐!\n");
		return sb.toString();
	}

	public static String secondMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("推送笑话!\n");
		return sb.toString();
	}
}