package com.particle.global.wxjava.cp.handler;

import com.particle.global.wxjava.cp.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 位置
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 14:40:51
 */
@Slf4j
public class LocationHandler extends AbstractHandler {

  @Override
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                  WxSessionManager sessionManager) {
    if (wxMessage.getMsgType().equals(WxConsts.XmlMsgType.LOCATION)) {
      //TODO 接收处理用户发送的地理位置消息
      try {
        String content = "感谢反馈，您的的地理位置已收到！";
        return new TextBuilder().build(content, wxMessage, null);
      } catch (Exception e) {
        log.error("位置消息接收处理失败", e);
        return null;
      }
    }

    //上报地理位置事件
    log.info("上报地理位置，纬度 : {} 经度 : {} 精度 : {}",
        wxMessage.getLatitude(), wxMessage.getLongitude(), String.valueOf(wxMessage.getPrecision()));

    //TODO  可以将用户地理位置信息保存到本地数据库，以便以后使用

    return null;
  }

}
