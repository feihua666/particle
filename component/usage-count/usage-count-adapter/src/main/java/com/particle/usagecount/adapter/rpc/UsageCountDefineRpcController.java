package com.particle.usagecount.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.usagecount.client.api.IUsageCountDefineApplicationService;
import com.particle.usagecount.adapter.feign.client.rpc.UsageCountDefineRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数定义远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Tag(name = "使用次数定义远程调用相关接口")
@RestController
@RequestMapping("/rpc/usage_count_define")
public class UsageCountDefineRpcController extends AbstractBaseRpcAdapter implements UsageCountDefineRpcFeignClient  {

	@Autowired
	private IUsageCountDefineApplicationService iUsageCountDefineApplicationService;


}