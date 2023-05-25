package com.particle.message.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.message.client.api.IMessageUserStateApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户消息读取状态后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Api(tags = "用户消息读取状态移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/message_user_state")
public class MessageUserStateAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IMessageUserStateApplicationService iMessageUserStateApplicationService;


}