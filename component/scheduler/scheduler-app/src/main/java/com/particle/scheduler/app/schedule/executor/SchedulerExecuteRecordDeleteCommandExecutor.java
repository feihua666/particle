package com.particle.scheduler.app.schedule.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.scheduler.app.schedule.structmapping.SchedulerExecuteRecordAppStructMapping;
import com.particle.scheduler.client.schedule.dto.data.SchedulerExecuteRecordVO;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecord;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecordId;
import com.particle.scheduler.domain.schedule.gateway.SchedulerExecuteRecordGateway;
import com.particle.scheduler.infrastructure.schedule.service.ISchedulerExecuteRecordService;
import com.particle.scheduler.infrastructure.schedule.dos.SchedulerExecuteRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 任务计划执行记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Component
@Validated
public class SchedulerExecuteRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerExecuteRecordGateway schedulerExecuteRecordGateway;
	private ISchedulerExecuteRecordService iSchedulerExecuteRecordService;

	/**
	 * 执行 任务计划执行记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<SchedulerExecuteRecordVO> execute(@Valid IdCommand deleteCommand) {
		SchedulerExecuteRecordId schedulerExecuteRecordId = SchedulerExecuteRecordId.of(deleteCommand.getId());
		SchedulerExecuteRecord byId = schedulerExecuteRecordGateway.getById(schedulerExecuteRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = schedulerExecuteRecordGateway.delete(schedulerExecuteRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(SchedulerExecuteRecordAppStructMapping.instance.toSchedulerExecuteRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
