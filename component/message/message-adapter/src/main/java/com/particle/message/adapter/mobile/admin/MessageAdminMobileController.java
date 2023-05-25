package com.particle.message.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.message.client.api.IMessageApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 消息后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Api(tags = "消息移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/message")
public class MessageAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IMessageApplicationService iMessageApplicationService;


}