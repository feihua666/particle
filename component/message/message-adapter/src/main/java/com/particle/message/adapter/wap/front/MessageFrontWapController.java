package com.particle.message.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.message.client.api.IMessageApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 消息前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Api(tags = "消息wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/message")
public class MessageFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IMessageApplicationService iMessageApplicationService;


}