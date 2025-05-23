package com.particle.scheduler.app.datatask.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.scheduler.app.datatask.structmapping.SchedulerAsyncDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTask;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTaskId;
import com.particle.scheduler.domain.datatask.gateway.SchedulerAsyncDataTaskGateway;
import com.particle.scheduler.infrastructure.datatask.service.ISchedulerAsyncDataTaskService;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerAsyncDataTaskDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 任务计划异步任务数据 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Component
@Validated
public class SchedulerAsyncDataTaskDeleteCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerAsyncDataTaskGateway schedulerAsyncDataTaskGateway;
	private ISchedulerAsyncDataTaskService iSchedulerAsyncDataTaskService;

	/**
	 * 执行 任务计划异步任务数据 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<SchedulerAsyncDataTaskVO> execute(@Valid IdCommand deleteCommand) {
		SchedulerAsyncDataTaskId schedulerAsyncDataTaskId = SchedulerAsyncDataTaskId.of(deleteCommand.getId());
		SchedulerAsyncDataTask byId = schedulerAsyncDataTaskGateway.getById(schedulerAsyncDataTaskId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = schedulerAsyncDataTaskGateway.delete(schedulerAsyncDataTaskId,deleteCommand);
		if (delete) {
			return SingleResponse.of(SchedulerAsyncDataTaskAppStructMapping.instance.toSchedulerAsyncDataTaskVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param schedulerAsyncDataTaskGateway
	 */
	@Autowired
	public void setSchedulerAsyncDataTaskGateway(SchedulerAsyncDataTaskGateway schedulerAsyncDataTaskGateway) {
		this.schedulerAsyncDataTaskGateway = schedulerAsyncDataTaskGateway;
	}
	@Autowired
	public void setISchedulerAsyncDataTaskService(ISchedulerAsyncDataTaskService iSchedulerAsyncDataTaskService) {
		this.iSchedulerAsyncDataTaskService = iSchedulerAsyncDataTaskService;
	}
}
