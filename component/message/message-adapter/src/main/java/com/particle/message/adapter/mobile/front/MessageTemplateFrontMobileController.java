package com.particle.message.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.message.client.api.IMessageTemplateApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 消息模板前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Api(tags = "消息模板移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/message_template")
public class MessageTemplateFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IMessageTemplateApplicationService iMessageTemplateApplicationService;


}