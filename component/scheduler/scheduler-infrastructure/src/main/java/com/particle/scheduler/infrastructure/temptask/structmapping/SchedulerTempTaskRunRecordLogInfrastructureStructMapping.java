package com.particle.scheduler.infrastructure.temptask.structmapping;

import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordLogDO;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLog;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLogId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 任务计划临时任务运行记录日志 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Mapper
public abstract class SchedulerTempTaskRunRecordLogInfrastructureStructMapping {
	public static SchedulerTempTaskRunRecordLogInfrastructureStructMapping instance = Mappers.getMapper( SchedulerTempTaskRunRecordLogInfrastructureStructMapping.class );

	protected SchedulerTempTaskRunRecordLogId map(Long id){
		if (id == null) {
			return null;
		}
		return SchedulerTempTaskRunRecordLogId.of(id);
	}
	protected Long map(SchedulerTempTaskRunRecordLogId schedulerTempTaskRunRecordLogId){
		if (schedulerTempTaskRunRecordLogId == null) {
			return null;
		}
		return schedulerTempTaskRunRecordLogId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerTempTaskRunRecordLogInfrastructureStructMapping#map(java.lang.Long)}
	 * @param schedulerTempTaskRunRecordLogDO
	 * @return
	 */
	public abstract SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLogDOToSchedulerTempTaskRunRecordLog(@MappingTarget SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog,SchedulerTempTaskRunRecordLogDO schedulerTempTaskRunRecordLogDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerTempTaskRunRecordLogInfrastructureStructMapping#map(SchedulerTempTaskRunRecordLogId)}
	 * @param schedulerTempTaskRunRecordLog
	 * @return
	 */
	public abstract SchedulerTempTaskRunRecordLogDO schedulerTempTaskRunRecordLogToSchedulerTempTaskRunRecordLogDO(SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog);

}
