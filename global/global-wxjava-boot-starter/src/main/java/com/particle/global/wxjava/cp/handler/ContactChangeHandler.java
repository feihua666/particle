package com.particle.global.wxjava.cp.handler;

import com.particle.global.tool.json.JsonTool;
import com.particle.global.wxjava.cp.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

import java.util.Map;


/**
 * <p>
 * 通讯录变更事件处理器
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 14:40:51
 */
@Slf4j
public class ContactChangeHandler extends AbstractHandler {

  @Override
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                  WxSessionManager sessionManager) {
    String content = "收到通讯录变更事件，内容：" + JsonTool.toJsonStr(wxMessage);
    log.info(content);

    return new TextBuilder().build(content, wxMessage, cpService);
  }

}
