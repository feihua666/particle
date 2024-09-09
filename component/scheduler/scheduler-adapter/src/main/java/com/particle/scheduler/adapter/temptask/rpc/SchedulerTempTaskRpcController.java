package com.particle.scheduler.adapter.temptask.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskApplicationService;
import com.particle.scheduler.adapter.feign.client.temptask.rpc.SchedulerTempTaskRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划临时任务远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Tag(name = "任务计划临时任务远程调用相关接口")
@RestController
@RequestMapping("/rpc/scheduler_temp_task")
public class SchedulerTempTaskRpcController extends AbstractBaseRpcAdapter implements SchedulerTempTaskRpcFeignClient  {

	@Autowired
	private ISchedulerTempTaskApplicationService iSchedulerTempTaskApplicationService;


}