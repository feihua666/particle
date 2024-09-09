package com.particle.scheduler.app.temptask.executor;

import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskRunRecordAppStructMapping;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordCreateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecord;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskRunRecordGateway;
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
 * 任务计划临时任务运行记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Component
@Validated
public class SchedulerTempTaskRunRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskRunRecordGateway schedulerTempTaskRunRecordGateway;

	/**
	 * 执行任务计划临时任务运行记录添加指令
	 * @param schedulerTempTaskRunRecordCreateCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskRunRecordVO> execute(@Valid SchedulerTempTaskRunRecordCreateCommand schedulerTempTaskRunRecordCreateCommand) {
		SchedulerTempTaskRunRecord schedulerTempTaskRunRecord = createBySchedulerTempTaskRunRecordCreateCommand(schedulerTempTaskRunRecordCreateCommand);
		schedulerTempTaskRunRecord.setAddControl(schedulerTempTaskRunRecordCreateCommand);
		boolean save = schedulerTempTaskRunRecordGateway.save(schedulerTempTaskRunRecord);
		if (save) {
			return SingleResponse.of(SchedulerTempTaskRunRecordAppStructMapping.instance.toSchedulerTempTaskRunRecordVO(schedulerTempTaskRunRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划临时任务运行记录创建指令创建任务计划临时任务运行记录模型
	 * @param schedulerTempTaskRunRecordCreateCommand
	 * @return
	 */
	private SchedulerTempTaskRunRecord createBySchedulerTempTaskRunRecordCreateCommand(SchedulerTempTaskRunRecordCreateCommand schedulerTempTaskRunRecordCreateCommand){
		SchedulerTempTaskRunRecord schedulerTempTaskRunRecord = SchedulerTempTaskRunRecord.create();
		SchedulerTempTaskRunRecordCreateCommandToSchedulerTempTaskRunRecordMapping.instance.fillSchedulerTempTaskRunRecordBySchedulerTempTaskRunRecordCreateCommand(schedulerTempTaskRunRecord, schedulerTempTaskRunRecordCreateCommand);
		return schedulerTempTaskRunRecord;
	}

	@Mapper
	interface  SchedulerTempTaskRunRecordCreateCommandToSchedulerTempTaskRunRecordMapping{
		SchedulerTempTaskRunRecordCreateCommandToSchedulerTempTaskRunRecordMapping instance = Mappers.getMapper( SchedulerTempTaskRunRecordCreateCommandToSchedulerTempTaskRunRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerTempTaskRunRecord
		 * @param schedulerTempTaskRunRecordCreateCommand
		 */
		void fillSchedulerTempTaskRunRecordBySchedulerTempTaskRunRecordCreateCommand(@MappingTarget SchedulerTempTaskRunRecord schedulerTempTaskRunRecord, SchedulerTempTaskRunRecordCreateCommand schedulerTempTaskRunRecordCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param schedulerTempTaskRunRecordGateway
	 */
	@Autowired
	public void setSchedulerTempTaskRunRecordGateway(SchedulerTempTaskRunRecordGateway schedulerTempTaskRunRecordGateway) {
		this.schedulerTempTaskRunRecordGateway = schedulerTempTaskRunRecordGateway;
	}
}
