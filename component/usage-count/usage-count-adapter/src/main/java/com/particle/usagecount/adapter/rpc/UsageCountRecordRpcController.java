package com.particle.usagecount.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.adapter.feign.client.rpc.UsageCountRecordRpcFeignClient;
import com.particle.usagecount.client.api.IUsageCountRecordApplicationService;
import com.particle.usagecount.client.dto.command.UsageCountRecordMarkCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordMarkVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Tag(name = "使用次数记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/usage_count_record")
public class UsageCountRecordRpcController extends AbstractBaseRpcAdapter implements UsageCountRecordRpcFeignClient  {

	@Autowired
	private IUsageCountRecordApplicationService iUsageCountRecordApplicationService;


	@Operation(description = "使用次数标记")
	@PostMapping("/mark")
	@Override
	public SingleResponse<UsageCountRecordMarkVO> mark(@RequestBody UsageCountRecordMarkCommand usageCountRecordMarkCommand) {
		return iUsageCountRecordApplicationService.mark(usageCountRecordMarkCommand);
	}
}
