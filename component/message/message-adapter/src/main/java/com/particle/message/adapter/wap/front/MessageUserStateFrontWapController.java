package com.particle.message.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.message.client.api.IMessageUserStateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户消息读取状态前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Tag(name = "用户消息读取状态wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/message_user_state")
public class MessageUserStateFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IMessageUserStateApplicationService iMessageUserStateApplicationService;


}