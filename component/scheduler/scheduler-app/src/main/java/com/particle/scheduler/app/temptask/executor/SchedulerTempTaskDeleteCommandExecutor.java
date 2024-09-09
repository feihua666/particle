package com.particle.scheduler.app.temptask.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskAppStructMapping;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTask;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskId;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskGateway;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordService;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskService;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 任务计划临时任务 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Component
@Validated
public class SchedulerTempTaskDeleteCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskGateway schedulerTempTaskGateway;
	private ISchedulerTempTaskService iSchedulerTempTaskService;
	private ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService;
	private SchedulerTempTaskRunRecordDeleteCommandExecutor schedulerTempTaskRunRecordDeleteCommandExecutor;

	/**
	 * 执行 任务计划临时任务 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskVO> execute(@Valid IdCommand deleteCommand) {
		SchedulerTempTaskId schedulerTempTaskId = SchedulerTempTaskId.of(deleteCommand.getId());
		SchedulerTempTask byId = schedulerTempTaskGateway.getById(schedulerTempTaskId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = schedulerTempTaskGateway.delete(schedulerTempTaskId,deleteCommand);
		if (delete) {
			// 删除成功后，将运行记录删除
			for (SchedulerTempTaskRunRecordDO schedulerTempTaskRunRecordDO : iSchedulerTempTaskRunRecordService.getBySchedulerTempTaskId(schedulerTempTaskId.getId())) {
				IdCommand idCommand = new IdCommand();
				idCommand.setId(schedulerTempTaskRunRecordDO.getId());
				schedulerTempTaskRunRecordDeleteCommandExecutor.execute(idCommand);
			}
			return SingleResponse.of(SchedulerTempTaskAppStructMapping.instance.toSchedulerTempTaskVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param schedulerTempTaskGateway
	 */
	@Autowired
	public void setSchedulerTempTaskGateway(SchedulerTempTaskGateway schedulerTempTaskGateway) {
		this.schedulerTempTaskGateway = schedulerTempTaskGateway;
	}
	@Autowired
	public void setISchedulerTempTaskService(ISchedulerTempTaskService iSchedulerTempTaskService) {
		this.iSchedulerTempTaskService = iSchedulerTempTaskService;
	}
	@Autowired
	public void setiSchedulerTempTaskRunRecordService(ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService) {
		this.iSchedulerTempTaskRunRecordService = iSchedulerTempTaskRunRecordService;
	}

	@Autowired
	public void setSchedulerTempTaskRunRecordDeleteCommandExecutor(SchedulerTempTaskRunRecordDeleteCommandExecutor schedulerTempTaskRunRecordDeleteCommandExecutor) {
		this.schedulerTempTaskRunRecordDeleteCommandExecutor = schedulerTempTaskRunRecordDeleteCommandExecutor;
	}
}
