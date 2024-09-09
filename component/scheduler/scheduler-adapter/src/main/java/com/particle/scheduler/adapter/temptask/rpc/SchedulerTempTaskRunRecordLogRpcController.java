package com.particle.scheduler.adapter.temptask.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskRunRecordLogApplicationService;
import com.particle.scheduler.adapter.feign.client.temptask.rpc.SchedulerTempTaskRunRecordLogRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划临时任务运行记录日志远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Tag(name = "任务计划临时任务运行记录日志远程调用相关接口")
@RestController
@RequestMapping("/rpc/scheduler_temp_task_run_record_log")
public class SchedulerTempTaskRunRecordLogRpcController extends AbstractBaseRpcAdapter implements SchedulerTempTaskRunRecordLogRpcFeignClient  {

	@Autowired
	private ISchedulerTempTaskRunRecordLogApplicationService iSchedulerTempTaskRunRecordLogApplicationService;


}