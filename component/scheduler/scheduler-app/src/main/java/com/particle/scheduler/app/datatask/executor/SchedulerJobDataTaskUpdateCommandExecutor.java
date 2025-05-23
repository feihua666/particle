package com.particle.scheduler.app.datatask.executor;

import com.particle.scheduler.app.datatask.structmapping.SchedulerJobDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.dto.command.SchedulerJobDataTaskUpdateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTask;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTaskId;
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
 * 任务计划任务数据 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class SchedulerJobDataTaskUpdateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerJobDataTaskGateway schedulerJobDataTaskGateway;

	/**
	 * 执行 任务计划任务数据 更新指令
	 * @param schedulerJobDataTaskUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerJobDataTaskVO> execute(@Valid SchedulerJobDataTaskUpdateCommand schedulerJobDataTaskUpdateCommand) {
		SchedulerJobDataTask schedulerJobDataTask = createBySchedulerJobDataTaskUpdateCommand(schedulerJobDataTaskUpdateCommand);
		schedulerJobDataTask.setUpdateControl(schedulerJobDataTaskUpdateCommand);
		boolean save = schedulerJobDataTaskGateway.save(schedulerJobDataTask);
		if (save) {
			return SingleResponse.of(SchedulerJobDataTaskAppStructMapping.instance.toSchedulerJobDataTaskVO(schedulerJobDataTask));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划任务数据更新指令创建任务计划任务数据模型
	 * @param schedulerJobDataTaskUpdateCommand
	 * @return
	 */
	private SchedulerJobDataTask createBySchedulerJobDataTaskUpdateCommand(SchedulerJobDataTaskUpdateCommand schedulerJobDataTaskUpdateCommand){
		SchedulerJobDataTask schedulerJobDataTask = SchedulerJobDataTask.create();
		SchedulerJobDataTaskUpdateCommandToSchedulerJobDataTaskMapping.instance.fillSchedulerJobDataTaskBySchedulerJobDataTaskUpdateCommand(schedulerJobDataTask, schedulerJobDataTaskUpdateCommand);
		return schedulerJobDataTask;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface SchedulerJobDataTaskUpdateCommandToSchedulerJobDataTaskMapping{
		SchedulerJobDataTaskUpdateCommandToSchedulerJobDataTaskMapping instance = Mappers.getMapper(SchedulerJobDataTaskUpdateCommandToSchedulerJobDataTaskMapping.class );

		default SchedulerJobDataTaskId map(Long id){
			if (id == null) {
				return null;
			}
			return SchedulerJobDataTaskId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerJobDataTask
		 * @param schedulerJobDataTaskUpdateCommand
		 */
		void fillSchedulerJobDataTaskBySchedulerJobDataTaskUpdateCommand(@MappingTarget SchedulerJobDataTask schedulerJobDataTask, SchedulerJobDataTaskUpdateCommand schedulerJobDataTaskUpdateCommand);
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
