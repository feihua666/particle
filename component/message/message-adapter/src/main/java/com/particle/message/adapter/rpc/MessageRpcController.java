package com.particle.message.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.message.client.api.IMessageApplicationService;
import com.particle.message.adapter.feign.client.rpc.MessageRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 消息远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Api(tags = "消息远程调用相关接口")
@RestController
@RequestMapping("/rpc/message")
public class MessageRpcController extends AbstractBaseRpcAdapter implements MessageRpcFeignClient  {

	@Autowired
	private IMessageApplicationService iMessageApplicationService;


}