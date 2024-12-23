package com.particle.usagecount.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.usagecount.adapter.feign.client.rpc.UsageCountRecordDetailRpcFeignClient;
import com.particle.usagecount.client.api.IUsageCountRecordDetailApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数记录明细远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Tag(name = "使用次数记录明细远程调用相关接口")
@RestController
@RequestMapping("/rpc/usage_count_record_detail")
public class UsageCountRecordDetailRpcController extends AbstractBaseRpcAdapter implements UsageCountRecordDetailRpcFeignClient  {

	@Autowired
	private IUsageCountRecordDetailApplicationService iUsageCountRecordDetailApplicationService;


}
