package com.particle.scheduler.app.datatask.executor;

import com.particle.scheduler.app.datatask.structmapping.SchedulerAsyncDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.dto.command.SchedulerAsyncDataTaskUpdateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTask;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTaskId;
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
 * 任务计划异步任务数据 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class SchedulerAsyncDataTaskUpdateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerAsyncDataTaskGateway schedulerAsyncDataTaskGateway;

	/**
	 * 执行 任务计划异步任务数据 更新指令
	 * @param schedulerAsyncDataTaskUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerAsyncDataTaskVO> execute(@Valid SchedulerAsyncDataTaskUpdateCommand schedulerAsyncDataTaskUpdateCommand) {
		SchedulerAsyncDataTask schedulerAsyncDataTask = createBySchedulerAsyncDataTaskUpdateCommand(schedulerAsyncDataTaskUpdateCommand);
		schedulerAsyncDataTask.setUpdateControl(schedulerAsyncDataTaskUpdateCommand);
		boolean save = schedulerAsyncDataTaskGateway.save(schedulerAsyncDataTask);
		if (save) {
			return SingleResponse.of(SchedulerAsyncDataTaskAppStructMapping.instance.toSchedulerAsyncDataTaskVO(schedulerAsyncDataTask));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划异步任务数据更新指令创建任务计划异步任务数据模型
	 * @param schedulerAsyncDataTaskUpdateCommand
	 * @return
	 */
	private SchedulerAsyncDataTask createBySchedulerAsyncDataTaskUpdateCommand(SchedulerAsyncDataTaskUpdateCommand schedulerAsyncDataTaskUpdateCommand){
		SchedulerAsyncDataTask schedulerAsyncDataTask = SchedulerAsyncDataTask.create();
		SchedulerAsyncDataTaskUpdateCommandToSchedulerAsyncDataTaskMapping.instance.fillSchedulerAsyncDataTaskBySchedulerAsyncDataTaskUpdateCommand(schedulerAsyncDataTask, schedulerAsyncDataTaskUpdateCommand);
		return schedulerAsyncDataTask;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface SchedulerAsyncDataTaskUpdateCommandToSchedulerAsyncDataTaskMapping{
		SchedulerAsyncDataTaskUpdateCommandToSchedulerAsyncDataTaskMapping instance = Mappers.getMapper(SchedulerAsyncDataTaskUpdateCommandToSchedulerAsyncDataTaskMapping.class );

		default SchedulerAsyncDataTaskId map(Long id){
			if (id == null) {
				return null;
			}
			return SchedulerAsyncDataTaskId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerAsyncDataTask
		 * @param schedulerAsyncDataTaskUpdateCommand
		 */
		void fillSchedulerAsyncDataTaskBySchedulerAsyncDataTaskUpdateCommand(@MappingTarget SchedulerAsyncDataTask schedulerAsyncDataTask, SchedulerAsyncDataTaskUpdateCommand schedulerAsyncDataTaskUpdateCommand);
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
