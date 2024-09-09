package com.particle.scheduler.app.temptask.executor;

import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskRunRecordAppStructMapping;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordUpdateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecord;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordId;
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
 * 任务计划临时任务运行记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class SchedulerTempTaskRunRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskRunRecordGateway schedulerTempTaskRunRecordGateway;

	/**
	 * 执行 任务计划临时任务运行记录 更新指令
	 * @param schedulerTempTaskRunRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskRunRecordVO> execute(@Valid SchedulerTempTaskRunRecordUpdateCommand schedulerTempTaskRunRecordUpdateCommand) {
		SchedulerTempTaskRunRecord schedulerTempTaskRunRecord = createBySchedulerTempTaskRunRecordUpdateCommand(schedulerTempTaskRunRecordUpdateCommand);
		schedulerTempTaskRunRecord.setUpdateControl(schedulerTempTaskRunRecordUpdateCommand);
		boolean save = schedulerTempTaskRunRecordGateway.save(schedulerTempTaskRunRecord);
		if (save) {
			return SingleResponse.of(SchedulerTempTaskRunRecordAppStructMapping.instance.toSchedulerTempTaskRunRecordVO(schedulerTempTaskRunRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据任务计划临时任务运行记录更新指令创建任务计划临时任务运行记录模型
	 * @param schedulerTempTaskRunRecordUpdateCommand
	 * @return
	 */
	private SchedulerTempTaskRunRecord createBySchedulerTempTaskRunRecordUpdateCommand(SchedulerTempTaskRunRecordUpdateCommand schedulerTempTaskRunRecordUpdateCommand){
		SchedulerTempTaskRunRecord schedulerTempTaskRunRecord = SchedulerTempTaskRunRecord.create();
		SchedulerTempTaskRunRecordUpdateCommandToSchedulerTempTaskRunRecordMapping.instance.fillSchedulerTempTaskRunRecordBySchedulerTempTaskRunRecordUpdateCommand(schedulerTempTaskRunRecord, schedulerTempTaskRunRecordUpdateCommand);
		return schedulerTempTaskRunRecord;
	}

	@Mapper
	interface SchedulerTempTaskRunRecordUpdateCommandToSchedulerTempTaskRunRecordMapping{
		SchedulerTempTaskRunRecordUpdateCommandToSchedulerTempTaskRunRecordMapping instance = Mappers.getMapper(SchedulerTempTaskRunRecordUpdateCommandToSchedulerTempTaskRunRecordMapping.class );

		default SchedulerTempTaskRunRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return SchedulerTempTaskRunRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param schedulerTempTaskRunRecord
		 * @param schedulerTempTaskRunRecordUpdateCommand
		 */
		void fillSchedulerTempTaskRunRecordBySchedulerTempTaskRunRecordUpdateCommand(@MappingTarget SchedulerTempTaskRunRecord schedulerTempTaskRunRecord, SchedulerTempTaskRunRecordUpdateCommand schedulerTempTaskRunRecordUpdateCommand);
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
