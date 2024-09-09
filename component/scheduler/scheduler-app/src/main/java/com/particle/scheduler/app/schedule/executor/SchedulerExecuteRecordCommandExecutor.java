package com.particle.scheduler.app.schedule.executor;

import com.particle.scheduler.domain.schedule.gateway.SchedulerExecuteRecordGateway;
import com.particle.scheduler.infrastructure.schedule.service.ISchedulerExecuteRecordService;
import com.particle.scheduler.infrastructure.schedule.dos.SchedulerExecuteRecordDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 任务计划执行记录 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Component
@Validated
public class SchedulerExecuteRecordCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerExecuteRecordGateway schedulerExecuteRecordGateway;
	private ISchedulerExecuteRecordService iSchedulerExecuteRecordService;
	/**
	 * 注入使用set方法
	 * @param schedulerExecuteRecordGateway
	 */
	@Autowired
	public void setSchedulerExecuteRecordGateway(SchedulerExecuteRecordGateway schedulerExecuteRecordGateway) {
		this.schedulerExecuteRecordGateway = schedulerExecuteRecordGateway;
	}
	@Autowired
	public void setISchedulerExecuteRecordService(ISchedulerExecuteRecordService iSchedulerExecuteRecordService) {
		this.iSchedulerExecuteRecordService = iSchedulerExecuteRecordService;
	}
}
