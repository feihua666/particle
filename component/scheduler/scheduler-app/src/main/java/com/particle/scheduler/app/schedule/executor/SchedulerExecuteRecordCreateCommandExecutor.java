package com.particle.scheduler.app.schedule.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.scheduler.app.schedule.structmapping.SchedulerExecuteRecordAppStructMapping;
import com.particle.scheduler.client.schedule.dto.command.SchedulerExecuteRecordCreateCommand;
import com.particle.scheduler.client.schedule.dto.data.SchedulerExecuteRecordVO;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecord;
import com.particle.scheduler.domain.schedule.gateway.SchedulerExecuteRecordGateway;
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
 * 任务计划执行记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Component
@Validated
public class SchedulerExecuteRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerExecuteRecordGateway schedulerExecuteRecordGateway;

	/**
	 * 执行任务计划执行记录添加指令
	 * @param schedulerExecuteRecordCreateCommand
	 * @return
	 */
	public SingleResponse<SchedulerExecuteRecordVO> execute(@Valid SchedulerExecuteRecordCreateCommand schedulerExecuteRecordCreateCommand) {
		SchedulerExecuteRecord schedulerExecuteRecord = createBySchedulerExecuteRecordCreateCommand(schedulerExecuteRecordCreateCommand);
		schedulerExecuteRecord.setAddControl(schedulerExecuteRecordCreateCommand);
		boolean save = schedulerExecuteRecordGateway.save(schedulerExecuteRecord);
		if (save) {
			return SingleResponse.of(SchedulerExecuteRecordAppStructMapping.instance.toSchedulerExecuteRecordVO(schedulerExecuteRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划执行记录创建指令创建任务计划执行记录模型
	 * @param schedulerExecuteRecordCreateCommand
	 * @return
	 */
	private SchedulerExecuteRecord createBySchedulerExecuteRecordCreateCommand(SchedulerExecuteRecordCreateCommand schedulerExecuteRecordCreateCommand){
		SchedulerExecuteRecord schedulerExecuteRecord = SchedulerExecuteRecord.create();
		SchedulerExecuteRecordCreateCommandToSchedulerExecuteRecordMapping.instance.fillSchedulerExecuteRecordBySchedulerExecuteRecordCreateCommand(schedulerExecuteRecord, schedulerExecuteRecordCreateCommand);
		return schedulerExecuteRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  SchedulerExecuteRecordCreateCommandToSchedulerExecuteRecordMapping{
		SchedulerExecuteRecordCreateCommandToSchedulerExecuteRecordMapping instance = Mappers.getMapper( SchedulerExecuteRecordCreateCommandToSchedulerExecuteRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerExecuteRecord
		 * @param schedulerExecuteRecordCreateCommand
		 */
		void fillSchedulerExecuteRecordBySchedulerExecuteRecordCreateCommand(@MappingTarget SchedulerExecuteRecord schedulerExecuteRecord, SchedulerExecuteRecordCreateCommand schedulerExecuteRecordCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param schedulerExecuteRecordGateway
	 */
	@Autowired
	public void setSchedulerExecuteRecordGateway(SchedulerExecuteRecordGateway schedulerExecuteRecordGateway) {
		this.schedulerExecuteRecordGateway = schedulerExecuteRecordGateway;
	}
}
