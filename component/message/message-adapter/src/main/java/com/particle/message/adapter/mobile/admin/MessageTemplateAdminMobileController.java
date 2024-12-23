package com.particle.message.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.message.client.api.IMessageTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 消息模板后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Tag(name = "消息模板移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/message_template")
public class MessageTemplateAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IMessageTemplateApplicationService iMessageTemplateApplicationService;


}
