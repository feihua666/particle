package com.particle.scheduler.infrastructure.temptask.structmapping;

import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecord;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordId;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 任务计划临时任务运行记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerTempTaskRunRecordInfrastructureStructMapping {
	public static SchedulerTempTaskRunRecordInfrastructureStructMapping instance = Mappers.getMapper( SchedulerTempTaskRunRecordInfrastructureStructMapping.class );

	protected SchedulerTempTaskRunRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return SchedulerTempTaskRunRecordId.of(id);
	}
	protected Long map(SchedulerTempTaskRunRecordId schedulerTempTaskRunRecordId){
		if (schedulerTempTaskRunRecordId == null) {
			return null;
		}
		return schedulerTempTaskRunRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerTempTaskRunRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param schedulerTempTaskRunRecordDO
	 * @return
	 */
	public abstract SchedulerTempTaskRunRecord schedulerTempTaskRunRecordDOToSchedulerTempTaskRunRecord(@MappingTarget SchedulerTempTaskRunRecord schedulerTempTaskRunRecord,SchedulerTempTaskRunRecordDO schedulerTempTaskRunRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerTempTaskRunRecordInfrastructureStructMapping#map(SchedulerTempTaskRunRecordId)}
	 * @param schedulerTempTaskRunRecord
	 * @return
	 */
	public abstract SchedulerTempTaskRunRecordDO schedulerTempTaskRunRecordToSchedulerTempTaskRunRecordDO(SchedulerTempTaskRunRecord schedulerTempTaskRunRecord);

}
