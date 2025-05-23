package com.particle.scheduler.adapter.datatask.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.scheduler.client.datatask.api.ISchedulerAsyncDataTaskApplicationService;
import com.particle.scheduler.adapter.feign.client.datatask.rpc.SchedulerAsyncDataTaskRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划异步任务数据远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Tag(name = "任务计划异步任务数据远程调用相关接口")
@RestController
@RequestMapping("/rpc/scheduler_async_data_task")
public class SchedulerAsyncDataTaskRpcController extends AbstractBaseRpcAdapter implements SchedulerAsyncDataTaskRpcFeignClient  {

	@Autowired
	private ISchedulerAsyncDataTaskApplicationService iSchedulerAsyncDataTaskApplicationService;


}