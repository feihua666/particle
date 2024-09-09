package com.particle.scheduler.adapter.temptask.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.scheduler.adapter.feign.client.temptask.rpc.SchedulerTempTaskControlRpcFeignClient;
import com.particle.scheduler.adapter.feign.client.temptask.rpc.SchedulerTempTaskRpcFeignClient;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskApplicationService;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskControlApplicationService;
import io.swagger.v3.oas.annotations.Operation;
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
public class SchedulerTempTaskControlRpcController extends AbstractBaseRpcAdapter implements SchedulerTempTaskControlRpcFeignClient {

	@Autowired
	private ISchedulerTempTaskApplicationService iSchedulerTempTaskApplicationService;

	@Autowired
	private ISchedulerTempTaskControlApplicationService iSchedulerTempTaskControlApplicationService;

	@Operation(summary = "临时任务开始")
	@Override
	public Long start(String code, String name) {
		return iSchedulerTempTaskControlApplicationService.start(code,name);
	}

	@Operation(summary = "临时任务结束")
	@Override
	public void finish(Long id, Boolean isHasError, String result) {
		iSchedulerTempTaskControlApplicationService.finish(id,isHasError,result);
	}

	@Operation(summary = "临时任务通用日志")
	@Override
	public void log(String level, Long id, String message) {
		iSchedulerTempTaskControlApplicationService.log(level,id,message);
	}
	@Operation(summary = "临时任务info日志")
	@Override
	public void logInfo(Long id, String message) {
		iSchedulerTempTaskControlApplicationService.logInfo(id,message);
	}
	@Operation(summary = "临时任务error日志")
	@Override
	public void logError(Long id, String message) {
		iSchedulerTempTaskControlApplicationService.logError(id,message);
	}
	@Operation(summary = "临时任务检查允许运行开关")
	@Override
	public boolean checkIsAllowRunSwitch(Long id) {
		return iSchedulerTempTaskControlApplicationService.checkIsAllowRunSwitch(id);
	}
}