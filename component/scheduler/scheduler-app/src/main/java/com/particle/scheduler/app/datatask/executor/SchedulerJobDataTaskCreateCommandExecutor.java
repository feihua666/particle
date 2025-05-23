package com.particle.scheduler.app.datatask.executor;

import com.particle.scheduler.app.datatask.structmapping.SchedulerJobDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.dto.command.SchedulerJobDataTaskCreateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTask;
import com.particle.scheduler.domain.datatask.gateway.SchedulerJobDataTaskGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class SchedulerJobDataTaskCreateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerJobDataTaskGateway schedulerJobDataTaskGateway;

	/**
	 * 执行任务计划任务数据添加指令
	 * @param schedulerJobDataTaskCreateCommand
	 * @return
	 */
	public SingleResponse<SchedulerJobDataTaskVO> execute(@Valid SchedulerJobDataTaskCreateCommand schedulerJobDataTaskCreateCommand) {
		SchedulerJobDataTask schedulerJobDataTask = createBySchedulerJobDataTaskCreateCommand(schedulerJobDataTaskCreateCommand);
		schedulerJobDataTask.setAddControl(schedulerJobDataTaskCreateCommand);
		boolean save = schedulerJobDataTaskGateway.save(schedulerJobDataTask);
		if (save) {
			return SingleResponse.of(SchedulerJobDataTaskAppStructMapping.instance.toSchedulerJobDataTaskVO(schedulerJobDataTask));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划任务数据创建指令创建任务计划任务数据模型
	 * @param schedulerJobDataTaskCreateCommand
	 * @return
	 */
	private SchedulerJobDataTask createBySchedulerJobDataTaskCreateCommand(SchedulerJobDataTaskCreateCommand schedulerJobDataTaskCreateCommand){
		SchedulerJobDataTask schedulerJobDataTask = SchedulerJobDataTask.create();
		SchedulerJobDataTaskCreateCommandToSchedulerJobDataTaskMapping.instance.fillSchedulerJobDataTaskBySchedulerJobDataTaskCreateCommand(schedulerJobDataTask, schedulerJobDataTaskCreateCommand);
		return schedulerJobDataTask;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  SchedulerJobDataTaskCreateCommandToSchedulerJobDataTaskMapping{
		SchedulerJobDataTaskCreateCommandToSchedulerJobDataTaskMapping instance = Mappers.getMapper( SchedulerJobDataTaskCreateCommandToSchedulerJobDataTaskMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerJobDataTask
		 * @param schedulerJobDataTaskCreateCommand
		 */
		void fillSchedulerJobDataTaskBySchedulerJobDataTaskCreateCommand(@MappingTarget SchedulerJobDataTask schedulerJobDataTask, SchedulerJobDataTaskCreateCommand schedulerJobDataTaskCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param schedulerJobDataTaskGateway
	 */
	@Autowired
	public void setSchedulerJobDataTaskGateway(SchedulerJobDataTaskGateway schedulerJobDataTaskGateway) {
		this.schedulerJobDataTaskGateway = schedulerJobDataTaskGateway;
	}
}
