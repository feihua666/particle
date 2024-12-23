package com.particle.scheduler.infrastructure.temptask.structmapping;

import com.particle.scheduler.domain.temptask.SchedulerTempTask;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskId;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 任务计划临时任务 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerTempTaskInfrastructureStructMapping {
	public static SchedulerTempTaskInfrastructureStructMapping instance = Mappers.getMapper( SchedulerTempTaskInfrastructureStructMapping.class );

	protected SchedulerTempTaskId map(Long id){
		if (id == null) {
			return null;
		}
		return SchedulerTempTaskId.of(id);
	}
	protected Long map(SchedulerTempTaskId schedulerTempTaskId){
		if (schedulerTempTaskId == null) {
			return null;
		}
		return schedulerTempTaskId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerTempTaskInfrastructureStructMapping#map(java.lang.Long)}
	 * @param schedulerTempTaskDO
	 * @return
	 */
	public abstract SchedulerTempTask schedulerTempTaskDOToSchedulerTempTask(@MappingTarget SchedulerTempTask schedulerTempTask,SchedulerTempTaskDO schedulerTempTaskDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerTempTaskInfrastructureStructMapping#map(SchedulerTempTaskId)}
	 * @param schedulerTempTask
	 * @return
	 */
	public abstract SchedulerTempTaskDO schedulerTempTaskToSchedulerTempTaskDO(SchedulerTempTask schedulerTempTask);

}
