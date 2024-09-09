package com.particle.scheduler.app.temptask.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskRunRecordLogAppStructMapping;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordLogVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLog;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLogId;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskRunRecordLogGateway;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordLogService;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordLogDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 任务计划临时任务运行记录日志 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Component
@Validated
public class SchedulerTempTaskRunRecordLogDeleteCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskRunRecordLogGateway schedulerTempTaskRunRecordLogGateway;
	private ISchedulerTempTaskRunRecordLogService iSchedulerTempTaskRunRecordLogService;

	/**
	 * 执行 任务计划临时任务运行记录日志 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskRunRecordLogVO> execute(@Valid IdCommand deleteCommand) {
		SchedulerTempTaskRunRecordLogId schedulerTempTaskRunRecordLogId = SchedulerTempTaskRunRecordLogId.of(deleteCommand.getId());
		SchedulerTempTaskRunRecordLog byId = schedulerTempTaskRunRecordLogGateway.getById(schedulerTempTaskRunRecordLogId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = schedulerTempTaskRunRecordLogGateway.delete(schedulerTempTaskRunRecordLogId,deleteCommand);
		if (delete) {
			return SingleResponse.of(SchedulerTempTaskRunRecordLogAppStructMapping.instance.toSchedulerTempTaskRunRecordLogVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
