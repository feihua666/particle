package com.particle.scheduler.app.temptask.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskAppStructMapping;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskUpdateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTask;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskId;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 任务计划临时任务 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class SchedulerTempTaskUpdateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskGateway schedulerTempTaskGateway;

	/**
	 * 执行 任务计划临时任务 更新指令
	 * @param schedulerTempTaskUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskVO> execute(@Valid SchedulerTempTaskUpdateCommand schedulerTempTaskUpdateCommand) {
		SchedulerTempTask schedulerTempTask = createBySchedulerTempTaskUpdateCommand(schedulerTempTaskUpdateCommand);
		schedulerTempTask.setUpdateControl(schedulerTempTaskUpdateCommand);
		boolean save = schedulerTempTaskGateway.save(schedulerTempTask);
		if (save) {
			return SingleResponse.of(SchedulerTempTaskAppStructMapping.instance.toSchedulerTempTaskVO(schedulerTempTask));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划临时任务更新指令创建任务计划临时任务模型
	 * @param schedulerTempTaskUpdateCommand
	 * @return
	 */
	private SchedulerTempTask createBySchedulerTempTaskUpdateCommand(SchedulerTempTaskUpdateCommand schedulerTempTaskUpdateCommand){
		SchedulerTempTask schedulerTempTask = SchedulerTempTask.create();
		SchedulerTempTaskUpdateCommandToSchedulerTempTaskMapping.instance.fillSchedulerTempTaskBySchedulerTempTaskUpdateCommand(schedulerTempTask, schedulerTempTaskUpdateCommand);
		return schedulerTempTask;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface SchedulerTempTaskUpdateCommandToSchedulerTempTaskMapping{
		SchedulerTempTaskUpdateCommandToSchedulerTempTaskMapping instance = Mappers.getMapper(SchedulerTempTaskUpdateCommandToSchedulerTempTaskMapping.class );

		default SchedulerTempTaskId map(Long id){
			if (id == null) {
				return null;
			}
			return SchedulerTempTaskId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerTempTask
		 * @param schedulerTempTaskUpdateCommand
		 */
		void fillSchedulerTempTaskBySchedulerTempTaskUpdateCommand(@MappingTarget SchedulerTempTask schedulerTempTask, SchedulerTempTaskUpdateCommand schedulerTempTaskUpdateCommand);
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
