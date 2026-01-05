package com.particle.scheduler.adapter.temptask.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.adapter.feign.client.temptask.rpc.SchedulerTempTaskControlRpcFeignClient;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskApplicationService;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskControlApplicationService;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskFinishCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskLogBaseCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskLogCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskStartCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
public class SchedulerTempTaskControlRpcController extends AbstractBaseRpcAdapter implements SchedulerTempTaskControlRpcFeignClient {

	@Autowired
	private ISchedulerTempTaskApplicationService iSchedulerTempTaskApplicationService;

	@Autowired
	private ISchedulerTempTaskControlApplicationService iSchedulerTempTaskControlApplicationService;

	@Operation(summary = "临时任务开始")
	@Override
	public SingleResponse<Long> start(@RequestBody SchedulerTempTaskStartCommand schedulerTempTaskStartCommand) {
		return iSchedulerTempTaskControlApplicationService.start(schedulerTempTaskStartCommand);
	}

	@Operation(summary = "临时任务结束")
	@Override
	public Response finish(SchedulerTempTaskFinishCommand schedulerTempTaskFinishCommand) {
		return iSchedulerTempTaskControlApplicationService.finish(schedulerTempTaskFinishCommand);
	}

	@Operation(summary = "临时任务通用日志")
	@Override
	public Response log(SchedulerTempTaskLogCommand schedulerTempTaskLogCommand) {
		return iSchedulerTempTaskControlApplicationService.log(schedulerTempTaskLogCommand);
	}
	@Operation(summary = "临时任务info日志")
	@Override
	public Response logInfo(SchedulerTempTaskLogBaseCommand schedulerTempTaskLogBaseCommand) {
		return iSchedulerTempTaskControlApplicationService.logInfo(schedulerTempTaskLogBaseCommand);
	}
	@Operation(summary = "临时任务error日志")
	@Override
	public Response logError(SchedulerTempTaskLogBaseCommand schedulerTempTaskLogBaseCommand) {
		return iSchedulerTempTaskControlApplicationService.logError(schedulerTempTaskLogBaseCommand);
	}
	@Operation(summary = "临时任务检查允许运行开关")
	@Override
	public Response checkIsAllowRunSwitch(Long id) {
		return iSchedulerTempTaskControlApplicationService.checkIsAllowRunSwitch(id);
	}
}
