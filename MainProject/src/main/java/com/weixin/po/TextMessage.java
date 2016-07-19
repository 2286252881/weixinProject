package com.weixin.po;

public class TextMessage extends BaseMessage
{
  private String Content;
  private String MsgId;

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