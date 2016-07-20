package com.weixin.po;



/**
 * @remark 封装文本消息类型
 * @author Administrator
 *
 */
public class TextMessage extends BaseMessage
{
  private String Content;//文本消息内容
  private String MsgId;//消息Id

  public String getContent()
  {
    return this.Content;
  }
  public void setContent(String content) {
    this.Content = content;
  }
  public String getMsgId() {
    return this.MsgId;
  }
  public void setMsgId(String msgId) {
    this.MsgId = msgId;
  }
}