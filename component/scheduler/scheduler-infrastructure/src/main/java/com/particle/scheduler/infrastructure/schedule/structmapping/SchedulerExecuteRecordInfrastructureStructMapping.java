package com.particle.scheduler.infrastructure.schedule.structmapping;

import com.particle.scheduler.domain.schedule.SchedulerExecuteRecord;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecordId;
import com.particle.scheduler.domain.schedule.value.SchedulerExecuteRecordClearParam;
import com.particle.scheduler.infrastructure.schedule.dos.SchedulerExecuteRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 任务计划执行记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerExecuteRecordInfrastructureStructMapping {
	public static SchedulerExecuteRecordInfrastructureStructMapping instance = Mappers.getMapper( SchedulerExecuteRecordInfrastructureStructMapping.class );

	protected SchedulerExecuteRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return SchedulerExecuteRecordId.of(id);
	}
	protected Long map(SchedulerExecuteRecordId schedulerExecuteRecordId){
		if (schedulerExecuteRecordId == null) {
			return null;
		}
		return schedulerExecuteRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerExecuteRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param schedulerExecuteRecordDO
	 * @return
	 */
	public abstract SchedulerExecuteRecord schedulerExecuteRecordDOToSchedulerExecuteRecord(@MappingTarget SchedulerExecuteRecord schedulerExecuteRecord,SchedulerExecuteRecordDO schedulerExecuteRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerExecuteRecordInfrastructureStructMapping#map(SchedulerExecuteRecordId)}
	 * @param schedulerExecuteRecord
	 * @return
	 */
	public abstract SchedulerExecuteRecordDO schedulerExecuteRecordToSchedulerExecuteRecordDO(SchedulerExecuteRecord schedulerExecuteRecord);

	public abstract SchedulerExecuteRecordDO schedulerExecuteRecordClearParamToSchedulerExecuteRecordDO(SchedulerExecuteRecordClearParam schedulerExecuteRecordClearParam);



}
