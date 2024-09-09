package com.particle.scheduler.app.temptask.executor;

import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskRunRecordGateway;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordService;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 任务计划临时任务运行记录 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Component
@Validated
public class SchedulerTempTaskRunRecordCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskRunRecordGateway schedulerTempTaskRunRecordGateway;
	private ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService;
	/**
	 * 注入使用set方法
	 * @param schedulerTempTaskRunRecordGateway
	 */
	@Autowired
	public void setSchedulerTempTaskRunRecordGateway(SchedulerTempTaskRunRecordGateway schedulerTempTaskRunRecordGateway) {
		this.schedulerTempTaskRunRecordGateway = schedulerTempTaskRunRecordGateway;
	}
	@Autowired
	public void setISchedulerTempTaskRunRecordService(ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService) {
		this.iSchedulerTempTaskRunRecordService = iSchedulerTempTaskRunRecordService;
	}
}
