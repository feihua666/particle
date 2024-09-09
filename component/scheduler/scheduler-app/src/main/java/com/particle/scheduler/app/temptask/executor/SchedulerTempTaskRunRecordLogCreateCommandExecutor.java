package com.particle.scheduler.app.temptask.executor;

import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskRunRecordLogAppStructMapping;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordLogCreateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordLogVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLog;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskRunRecordLogGateway;
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
 * 任务计划临时任务运行记录日志 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Component
@Validated
public class SchedulerTempTaskRunRecordLogCreateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskRunRecordLogGateway schedulerTempTaskRunRecordLogGateway;

	/**
	 * 执行任务计划临时任务运行记录日志添加指令
	 * @param schedulerTempTaskRunRecordLogCreateCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskRunRecordLogVO> execute(@Valid SchedulerTempTaskRunRecordLogCreateCommand schedulerTempTaskRunRecordLogCreateCommand) {
		SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog = createBySchedulerTempTaskRunRecordLogCreateCommand(schedulerTempTaskRunRecordLogCreateCommand);
		schedulerTempTaskRunRecordLog.setAddControl(schedulerTempTaskRunRecordLogCreateCommand);
		boolean save = schedulerTempTaskRunRecordLogGateway.save(schedulerTempTaskRunRecordLog);
		if (save) {
			return SingleResponse.of(SchedulerTempTaskRunRecordLogAppStructMapping.instance.toSchedulerTempTaskRunRecordLogVO(schedulerTempTaskRunRecordLog));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划临时任务运行记录日志创建指令创建任务计划临时任务运行记录日志模型
	 * @param schedulerTempTaskRunRecordLogCreateCommand
	 * @return
	 */
	private SchedulerTempTaskRunRecordLog createBySchedulerTempTaskRunRecordLogCreateCommand(SchedulerTempTaskRunRecordLogCreateCommand schedulerTempTaskRunRecordLogCreateCommand){
		SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog = SchedulerTempTaskRunRecordLog.create();
		SchedulerTempTaskRunRecordLogCreateCommandToSchedulerTempTaskRunRecordLogMapping.instance.fillSchedulerTempTaskRunRecordLogBySchedulerTempTaskRunRecordLogCreateCommand(schedulerTempTaskRunRecordLog, schedulerTempTaskRunRecordLogCreateCommand);
		return schedulerTempTaskRunRecordLog;
	}

	@Mapper
	interface  SchedulerTempTaskRunRecordLogCreateCommandToSchedulerTempTaskRunRecordLogMapping{
		SchedulerTempTaskRunRecordLogCreateCommandToSchedulerTempTaskRunRecordLogMapping instance = Mappers.getMapper( SchedulerTempTaskRunRecordLogCreateCommandToSchedulerTempTaskRunRecordLogMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerTempTaskRunRecordLog
		 * @param schedulerTempTaskRunRecordLogCreateCommand
		 */
		void fillSchedulerTempTaskRunRecordLogBySchedulerTempTaskRunRecordLogCreateCommand(@MappingTarget SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog, SchedulerTempTaskRunRecordLogCreateCommand schedulerTempTaskRunRecordLogCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param schedulerTempTaskRunRecordLogGateway
	 */
	@Autowired
	public void setSchedulerTempTaskRunRecordLogGateway(SchedulerTempTaskRunRecordLogGateway schedulerTempTaskRunRecordLogGateway) {
		this.schedulerTempTaskRunRecordLogGateway = schedulerTempTaskRunRecordLogGateway;
	}
}
