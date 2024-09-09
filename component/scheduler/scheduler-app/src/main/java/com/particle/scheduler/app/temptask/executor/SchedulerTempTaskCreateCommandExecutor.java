package com.particle.scheduler.app.temptask.executor;

import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskAppStructMapping;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskCreateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTask;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class SchedulerTempTaskCreateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskGateway schedulerTempTaskGateway;

	/**
	 * 执行任务计划临时任务添加指令
	 * @param schedulerTempTaskCreateCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskVO> execute(@Valid SchedulerTempTaskCreateCommand schedulerTempTaskCreateCommand) {
		SchedulerTempTask schedulerTempTask = createBySchedulerTempTaskCreateCommand(schedulerTempTaskCreateCommand);
		schedulerTempTask.setAddControl(schedulerTempTaskCreateCommand);
		boolean save = schedulerTempTaskGateway.save(schedulerTempTask);
		if (save) {
			return SingleResponse.of(SchedulerTempTaskAppStructMapping.instance.toSchedulerTempTaskVO(schedulerTempTask));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划临时任务创建指令创建任务计划临时任务模型
	 * @param schedulerTempTaskCreateCommand
	 * @return
	 */
	private SchedulerTempTask createBySchedulerTempTaskCreateCommand(SchedulerTempTaskCreateCommand schedulerTempTaskCreateCommand){
		SchedulerTempTask schedulerTempTask = SchedulerTempTask.create();
		SchedulerTempTaskCreateCommandToSchedulerTempTaskMapping.instance.fillSchedulerTempTaskBySchedulerTempTaskCreateCommand(schedulerTempTask, schedulerTempTaskCreateCommand);
		return schedulerTempTask;
	}

	@Mapper
	interface  SchedulerTempTaskCreateCommandToSchedulerTempTaskMapping{
		SchedulerTempTaskCreateCommandToSchedulerTempTaskMapping instance = Mappers.getMapper( SchedulerTempTaskCreateCommandToSchedulerTempTaskMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerTempTask
		 * @param schedulerTempTaskCreateCommand
		 */
		void fillSchedulerTempTaskBySchedulerTempTaskCreateCommand(@MappingTarget SchedulerTempTask schedulerTempTask, SchedulerTempTaskCreateCommand schedulerTempTaskCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param schedulerTempTaskGateway
	 */
	@Autowired
	public void setSchedulerTempTaskGateway(SchedulerTempTaskGateway schedulerTempTaskGateway) {
		this.schedulerTempTaskGateway = schedulerTempTaskGateway;
	}
}
