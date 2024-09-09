package com.particle.scheduler.adapter.schedule.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.scheduler.client.schedule.api.ISchedulerExecuteRecordApplicationService;
import com.particle.scheduler.adapter.feign.client.schedule.rpc.SchedulerExecuteRecordRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划执行记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Tag(name = "任务计划执行记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/scheduler_execute_record")
public class SchedulerExecuteRecordRpcController extends AbstractBaseRpcAdapter implements SchedulerExecuteRecordRpcFeignClient  {

	@Autowired
	private ISchedulerExecuteRecordApplicationService iSchedulerExecuteRecordApplicationService;


}