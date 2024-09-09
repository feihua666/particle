package com.particle.scheduler.app.temptask.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskRunRecordAppStructMapping;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecord;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordId;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskRunRecordGateway;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordLogDO;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordLogService;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordService;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 任务计划临时任务运行记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Component
@Validated
public class SchedulerTempTaskRunRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskRunRecordGateway schedulerTempTaskRunRecordGateway;
	private ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService;
	private ISchedulerTempTaskRunRecordLogService iSchedulerTempTaskRunRecordLogService;

	/**
	 * 执行 任务计划临时任务运行记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskRunRecordVO> execute(@Valid IdCommand deleteCommand) {
		SchedulerTempTaskRunRecordId schedulerTempTaskRunRecordId = SchedulerTempTaskRunRecordId.of(deleteCommand.getId());
		SchedulerTempTaskRunRecord byId = schedulerTempTaskRunRecordGateway.getById(schedulerTempTaskRunRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = schedulerTempTaskRunRecordGateway.delete(schedulerTempTaskRunRecordId,deleteCommand);
		if (delete) {
			// 删除成功后，将日志也删除
			iSchedulerTempTaskRunRecordLogService.deleteByColumn(byId.getId().getId(), SchedulerTempTaskRunRecordLogDO::getSchedulerTempTaskRunRecordId);
			return SingleResponse.of(SchedulerTempTaskRunRecordAppStructMapping.instance.toSchedulerTempTaskRunRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
	@Autowired
	public void setISchedulerTempTaskRunRecordLogService(ISchedulerTempTaskRunRecordLogService iSchedulerTempTaskRunRecordLogService) {
		this.iSchedulerTempTaskRunRecordLogService = iSchedulerTempTaskRunRecordLogService;
	}
}
