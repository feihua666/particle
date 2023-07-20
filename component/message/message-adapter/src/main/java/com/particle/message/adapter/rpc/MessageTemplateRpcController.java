package com.particle.message.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.message.client.api.IMessageTemplateApplicationService;
import com.particle.message.adapter.feign.client.rpc.MessageTemplateRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 消息模板远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Tag(name = "消息模板远程调用相关接口")
@RestController
@RequestMapping("/rpc/message_template")
public class MessageTemplateRpcController extends AbstractBaseRpcAdapter implements MessageTemplateRpcFeignClient  {

	@Autowired
	private IMessageTemplateApplicationService iMessageTemplateApplicationService;


}