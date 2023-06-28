package com.particle.global.wxjava.cp.builder;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutImageMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;


/**
 * <p>
 * 图片消息构建器
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 14:40:51
 */
public class ImageBuilder extends AbstractBuilder {

  @Override
  public WxCpXmlOutMessage build(String content, WxCpXmlMessage wxMessage,
                                 WxCpService service) {

    WxCpXmlOutImageMessage m = WxCpXmlOutMessage.IMAGE().mediaId(content)
        .fromUser(wxMessage.getToUserName()).toUser(wxMessage.getFromUserName())
        .build();

    return m;
  }

}
