package com.particle.scheduler.app.temptask.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskRunRecordLogAppStructMapping;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordLogUpdateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordLogVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLog;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLogId;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskRunRecordLogGateway;
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
 * 任务计划临时任务运行记录日志 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class SchedulerTempTaskRunRecordLogUpdateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskRunRecordLogGateway schedulerTempTaskRunRecordLogGateway;

	/**
	 * 执行 任务计划临时任务运行记录日志 更新指令
	 * @param schedulerTempTaskRunRecordLogUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskRunRecordLogVO> execute(@Valid SchedulerTempTaskRunRecordLogUpdateCommand schedulerTempTaskRunRecordLogUpdateCommand) {
		SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog = createBySchedulerTempTaskRunRecordLogUpdateCommand(schedulerTempTaskRunRecordLogUpdateCommand);
		schedulerTempTaskRunRecordLog.setUpdateControl(schedulerTempTaskRunRecordLogUpdateCommand);
		boolean save = schedulerTempTaskRunRecordLogGateway.save(schedulerTempTaskRunRecordLog);
		if (save) {
			return SingleResponse.of(SchedulerTempTaskRunRecordLogAppStructMapping.instance.toSchedulerTempTaskRunRecordLogVO(schedulerTempTaskRunRecordLog));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划临时任务运行记录日志更新指令创建任务计划临时任务运行记录日志模型
	 * @param schedulerTempTaskRunRecordLogUpdateCommand
	 * @return
	 */
	private SchedulerTempTaskRunRecordLog createBySchedulerTempTaskRunRecordLogUpdateCommand(SchedulerTempTaskRunRecordLogUpdateCommand schedulerTempTaskRunRecordLogUpdateCommand){
		SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog = SchedulerTempTaskRunRecordLog.create();
		SchedulerTempTaskRunRecordLogUpdateCommandToSchedulerTempTaskRunRecordLogMapping.instance.fillSchedulerTempTaskRunRecordLogBySchedulerTempTaskRunRecordLogUpdateCommand(schedulerTempTaskRunRecordLog, schedulerTempTaskRunRecordLogUpdateCommand);
		return schedulerTempTaskRunRecordLog;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface SchedulerTempTaskRunRecordLogUpdateCommandToSchedulerTempTaskRunRecordLogMapping{
		SchedulerTempTaskRunRecordLogUpdateCommandToSchedulerTempTaskRunRecordLogMapping instance = Mappers.getMapper(SchedulerTempTaskRunRecordLogUpdateCommandToSchedulerTempTaskRunRecordLogMapping.class );

		default SchedulerTempTaskRunRecordLogId map(Long id){
			if (id == null) {
				return null;
			}
			return SchedulerTempTaskRunRecordLogId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerTempTaskRunRecordLog
		 * @param schedulerTempTaskRunRecordLogUpdateCommand
		 */
		void fillSchedulerTempTaskRunRecordLogBySchedulerTempTaskRunRecordLogUpdateCommand(@MappingTarget SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog, SchedulerTempTaskRunRecordLogUpdateCommand schedulerTempTaskRunRecordLogUpdateCommand);
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
