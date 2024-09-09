package com.particle.scheduler.app.temptask.executor;

import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskRunRecordLogGateway;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordLogService;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordLogDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 任务计划临时任务运行记录日志 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Component
@Validated
public class SchedulerTempTaskRunRecordLogCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskRunRecordLogGateway schedulerTempTaskRunRecordLogGateway;
	private ISchedulerTempTaskRunRecordLogService iSchedulerTempTaskRunRecordLogService;
	/**
	 * 注入使用set方法
	 * @param schedulerTempTaskRunRecordLogGateway
	 */
	@Autowired
	public void setSchedulerTempTaskRunRecordLogGateway(SchedulerTempTaskRunRecordLogGateway schedulerTempTaskRunRecordLogGateway) {
		this.schedulerTempTaskRunRecordLogGateway = schedulerTempTaskRunRecordLogGateway;
	}
	@Autowired
	public void setISchedulerTempTaskRunRecordLogService(ISchedulerTempTaskRunRecordLogService iSchedulerTempTaskRunRecordLogService) {
		this.iSchedulerTempTaskRunRecordLogService = iSchedulerTempTaskRunRecordLogService;
	}
}
