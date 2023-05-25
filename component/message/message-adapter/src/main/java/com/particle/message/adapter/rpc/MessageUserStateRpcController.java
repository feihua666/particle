package com.particle.message.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.message.client.api.IMessageUserStateApplicationService;
import com.particle.message.adapter.feign.client.rpc.MessageUserStateRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户消息读取状态远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Api(tags = "用户消息读取状态远程调用相关接口")
@RestController
@RequestMapping("/rpc/message_user_state")
public class MessageUserStateRpcController extends AbstractBaseRpcAdapter implements MessageUserStateRpcFeignClient  {

	@Autowired
	private IMessageUserStateApplicationService iMessageUserStateApplicationService;


}