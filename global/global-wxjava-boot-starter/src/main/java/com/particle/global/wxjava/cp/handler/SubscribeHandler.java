package com.particle.global.wxjava.cp.handler;

import com.particle.global.wxjava.cp.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

import java.util.Map;

/**
 * <p>
 * 订阅关注处理
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 14:40:51
 */
@Slf4j
public class SubscribeHandler extends AbstractHandler {

  @Override
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                  WxSessionManager sessionManager) throws WxErrorException {

    log.info("新关注用户 OPENID: " + wxMessage.getFromUserName());

    // 获取微信用户基本信息
    WxCpUser userWxInfo = cpService.getUserService().getById(wxMessage.getFromUserName());

    if (userWxInfo != null) {
      // TODO 可以添加关注用户到本地
    }

    WxCpXmlOutMessage responseResult = null;
    try {
      responseResult = handleSpecial(wxMessage);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    if (responseResult != null) {
      return responseResult;
    }

    try {
      return new TextBuilder().build("感谢关注", wxMessage, cpService);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    return null;
  }

  /**
   * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
   */
  private WxCpXmlOutMessage handleSpecial(WxCpXmlMessage wxMessage) {
    //TODO
    return null;
  }

}
