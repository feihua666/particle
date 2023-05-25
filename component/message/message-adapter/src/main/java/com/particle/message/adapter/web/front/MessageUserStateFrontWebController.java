package com.particle.message.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.message.client.api.IMessageUserStateApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户消息读取状态前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Api(tags = "用户消息读取状态pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/message_user_state")
public class MessageUserStateFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IMessageUserStateApplicationService iMessageUserStateApplicationService;


}