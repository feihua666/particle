package com.particle.scheduler.app.schedule.executor;

import com.particle.scheduler.app.schedule.structmapping.SchedulerExecuteRecordAppStructMapping;
import com.particle.scheduler.client.schedule.dto.command.SchedulerExecuteRecordUpdateCommand;
import com.particle.scheduler.client.schedule.dto.data.SchedulerExecuteRecordVO;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecord;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecordId;
import com.particle.scheduler.domain.schedule.gateway.SchedulerExecuteRecordGateway;
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
 * 任务计划执行记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class SchedulerExecuteRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerExecuteRecordGateway schedulerExecuteRecordGateway;

	/**
	 * 执行 任务计划执行记录 更新指令
	 * @param schedulerExecuteRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerExecuteRecordVO> execute(@Valid SchedulerExecuteRecordUpdateCommand schedulerExecuteRecordUpdateCommand) {
		SchedulerExecuteRecord schedulerExecuteRecord = createBySchedulerExecuteRecordUpdateCommand(schedulerExecuteRecordUpdateCommand);
		schedulerExecuteRecord.setUpdateControl(schedulerExecuteRecordUpdateCommand);
		boolean save = schedulerExecuteRecordGateway.save(schedulerExecuteRecord);
		if (save) {
			return SingleResponse.of(SchedulerExecuteRecordAppStructMapping.instance.toSchedulerExecuteRecordVO(schedulerExecuteRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划执行记录更新指令创建任务计划执行记录模型
	 * @param schedulerExecuteRecordUpdateCommand
	 * @return
	 */
	private SchedulerExecuteRecord createBySchedulerExecuteRecordUpdateCommand(SchedulerExecuteRecordUpdateCommand schedulerExecuteRecordUpdateCommand){
		SchedulerExecuteRecord schedulerExecuteRecord = SchedulerExecuteRecord.create();
		SchedulerExecuteRecordUpdateCommandToSchedulerExecuteRecordMapping.instance.fillSchedulerExecuteRecordBySchedulerExecuteRecordUpdateCommand(schedulerExecuteRecord, schedulerExecuteRecordUpdateCommand);
		return schedulerExecuteRecord;
	}

	@Mapper
	interface SchedulerExecuteRecordUpdateCommandToSchedulerExecuteRecordMapping{
		SchedulerExecuteRecordUpdateCommandToSchedulerExecuteRecordMapping instance = Mappers.getMapper(SchedulerExecuteRecordUpdateCommandToSchedulerExecuteRecordMapping.class );

		default SchedulerExecuteRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return SchedulerExecuteRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerExecuteRecord
		 * @param schedulerExecuteRecordUpdateCommand
		 */
		void fillSchedulerExecuteRecordBySchedulerExecuteRecordUpdateCommand(@MappingTarget SchedulerExecuteRecord schedulerExecuteRecord, SchedulerExecuteRecordUpdateCommand schedulerExecuteRecordUpdateCommand);
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
