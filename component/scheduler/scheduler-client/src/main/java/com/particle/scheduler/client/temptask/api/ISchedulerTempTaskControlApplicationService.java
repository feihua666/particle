package com.particle.scheduler.client.temptask.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskFinishCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskLogBaseCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskLogCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskStartCommand;

/**
 * <p>
 * 任务计划临时任务 控制应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
public interface ISchedulerTempTaskControlApplicationService extends IBaseApplicationService {
	/**
	 * 启动一个临时任务
	 * 该方法只会记录任务状态，不会执行任务逻辑
	 * @param schedulerTempTaskStartCommand
	 * @return 返回任务运行记录的id
	 */
	SingleResponse<Long> start(SchedulerTempTaskStartCommand schedulerTempTaskStartCommand);

	/**
	 * 完成临时任务
	 * @param schedulerTempTaskFinishCommand
	 */
	Response finish(SchedulerTempTaskFinishCommand schedulerTempTaskFinishCommand);

	/**
	 * 记录日志，记录运行记录的日志
	 * @param schedulerTempTaskLogCommand
	 */
	Response log(SchedulerTempTaskLogCommand schedulerTempTaskLogCommand);
    /**
	 * 默认的日志级别为info
	 * @param schedulerTempTaskLogBaseCommand
	 */
	Response logInfo(SchedulerTempTaskLogBaseCommand schedulerTempTaskLogBaseCommand);
    /**
	 * 默认的日志级别为error
	 * @param schedulerTempTaskLogBaseCommand
	 */
	Response logError(SchedulerTempTaskLogBaseCommand schedulerTempTaskLogBaseCommand);

	/**
	 * 检查是否允许运行开关状态，用来判断是否停止程序任务运行，在程序运行过程中，应该时刻调用该方法以检测是否允许运行，因为在后台管理界面上提供了开关，用来控制是否允许运行
	 * @param id 任务运行记录的id
	 * @return
	 */
    Response checkIsAllowRunSwitch(Long id);
}
