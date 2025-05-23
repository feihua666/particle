package com.particle.scheduler.app.datatask.executor;

import com.particle.scheduler.app.datatask.structmapping.SchedulerAsyncDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.dto.command.SchedulerAsyncDataTaskCreateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTask;
import com.particle.scheduler.domain.datatask.gateway.SchedulerAsyncDataTaskGateway;
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
 * 任务计划异步任务数据 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Component
@Validated
public class SchedulerAsyncDataTaskCreateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerAsyncDataTaskGateway schedulerAsyncDataTaskGateway;

	/**
	 * 执行任务计划异步任务数据添加指令
	 * @param schedulerAsyncDataTaskCreateCommand
	 * @return
	 */
	public SingleResponse<SchedulerAsyncDataTaskVO> execute(@Valid SchedulerAsyncDataTaskCreateCommand schedulerAsyncDataTaskCreateCommand) {
		SchedulerAsyncDataTask schedulerAsyncDataTask = createBySchedulerAsyncDataTaskCreateCommand(schedulerAsyncDataTaskCreateCommand);
		schedulerAsyncDataTask.setAddControl(schedulerAsyncDataTaskCreateCommand);
		boolean save = schedulerAsyncDataTaskGateway.save(schedulerAsyncDataTask);
		if (save) {
			return SingleResponse.of(SchedulerAsyncDataTaskAppStructMapping.instance.toSchedulerAsyncDataTaskVO(schedulerAsyncDataTask));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划异步任务数据创建指令创建任务计划异步任务数据模型
	 * @param schedulerAsyncDataTaskCreateCommand
	 * @return
	 */
	private SchedulerAsyncDataTask createBySchedulerAsyncDataTaskCreateCommand(SchedulerAsyncDataTaskCreateCommand schedulerAsyncDataTaskCreateCommand){
		SchedulerAsyncDataTask schedulerAsyncDataTask = SchedulerAsyncDataTask.create();
		SchedulerAsyncDataTaskCreateCommandToSchedulerAsyncDataTaskMapping.instance.fillSchedulerAsyncDataTaskBySchedulerAsyncDataTaskCreateCommand(schedulerAsyncDataTask, schedulerAsyncDataTaskCreateCommand);
		return schedulerAsyncDataTask;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  SchedulerAsyncDataTaskCreateCommandToSchedulerAsyncDataTaskMapping{
		SchedulerAsyncDataTaskCreateCommandToSchedulerAsyncDataTaskMapping instance = Mappers.getMapper( SchedulerAsyncDataTaskCreateCommandToSchedulerAsyncDataTaskMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerAsyncDataTask
		 * @param schedulerAsyncDataTaskCreateCommand
		 */
		void fillSchedulerAsyncDataTaskBySchedulerAsyncDataTaskCreateCommand(@MappingTarget SchedulerAsyncDataTask schedulerAsyncDataTask, SchedulerAsyncDataTaskCreateCommand schedulerAsyncDataTaskCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param schedulerAsyncDataTaskGateway
	 */
	@Autowired
	public void setSchedulerAsyncDataTaskGateway(SchedulerAsyncDataTaskGateway schedulerAsyncDataTaskGateway) {
		this.schedulerAsyncDataTaskGateway = schedulerAsyncDataTaskGateway;
	}
}
