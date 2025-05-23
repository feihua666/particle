package com.particle.scheduler.app.datatask.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.scheduler.app.datatask.structmapping.SchedulerJobDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTask;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTaskId;
import com.particle.scheduler.domain.datatask.gateway.SchedulerJobDataTaskGateway;
import com.particle.scheduler.infrastructure.datatask.service.ISchedulerJobDataTaskService;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerJobDataTaskDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 任务计划任务数据 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Component
@Validated
public class SchedulerJobDataTaskDeleteCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerJobDataTaskGateway schedulerJobDataTaskGateway;
	private ISchedulerJobDataTaskService iSchedulerJobDataTaskService;

	/**
	 * 执行 任务计划任务数据 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<SchedulerJobDataTaskVO> execute(@Valid IdCommand deleteCommand) {
		SchedulerJobDataTaskId schedulerJobDataTaskId = SchedulerJobDataTaskId.of(deleteCommand.getId());
		SchedulerJobDataTask byId = schedulerJobDataTaskGateway.getById(schedulerJobDataTaskId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = schedulerJobDataTaskGateway.delete(schedulerJobDataTaskId,deleteCommand);
		if (delete) {
			return SingleResponse.of(SchedulerJobDataTaskAppStructMapping.instance.toSchedulerJobDataTaskVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param schedulerJobDataTaskGateway
	 */
	@Autowired
	public void setSchedulerJobDataTaskGateway(SchedulerJobDataTaskGateway schedulerJobDataTaskGateway) {
		this.schedulerJobDataTaskGateway = schedulerJobDataTaskGateway;
	}
	@Autowired
	public void setISchedulerJobDataTaskService(ISchedulerJobDataTaskService iSchedulerJobDataTaskService) {
		this.iSchedulerJobDataTaskService = iSchedulerJobDataTaskService;
	}
}
