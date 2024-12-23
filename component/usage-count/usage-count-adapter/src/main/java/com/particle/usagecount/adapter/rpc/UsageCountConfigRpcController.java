package com.particle.usagecount.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.usagecount.adapter.feign.client.rpc.UsageCountConfigRpcFeignClient;
import com.particle.usagecount.client.api.IUsageCountConfigApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数配置远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Tag(name = "使用次数配置远程调用相关接口")
@RestController
@RequestMapping("/rpc/usage_count_config")
public class UsageCountConfigRpcController extends AbstractBaseRpcAdapter implements UsageCountConfigRpcFeignClient  {

	@Autowired
	private IUsageCountConfigApplicationService iUsageCountConfigApplicationService;


}
