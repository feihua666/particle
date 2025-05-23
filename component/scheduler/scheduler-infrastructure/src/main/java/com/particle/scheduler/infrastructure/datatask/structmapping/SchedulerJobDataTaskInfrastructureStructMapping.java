package com.particle.scheduler.infrastructure.datatask.structmapping;

import com.particle.scheduler.infrastructure.datatask.dos.SchedulerJobDataTaskDO;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTask;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTaskId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 任务计划任务数据 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerJobDataTaskInfrastructureStructMapping {
	public static SchedulerJobDataTaskInfrastructureStructMapping instance = Mappers.getMapper( SchedulerJobDataTaskInfrastructureStructMapping.class );

	protected SchedulerJobDataTaskId map(Long id){
		if (id == null) {
			return null;
		}
		return SchedulerJobDataTaskId.of(id);
	}
	protected Long map(SchedulerJobDataTaskId schedulerJobDataTaskId){
		if (schedulerJobDataTaskId == null) {
			return null;
		}
		return schedulerJobDataTaskId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerJobDataTaskInfrastructureStructMapping#map(java.lang.Long)}
	 * @param schedulerJobDataTaskDO
	 * @return
	 */
	public abstract SchedulerJobDataTask schedulerJobDataTaskDOToSchedulerJobDataTask(@MappingTarget SchedulerJobDataTask schedulerJobDataTask,SchedulerJobDataTaskDO schedulerJobDataTaskDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerJobDataTaskInfrastructureStructMapping#map(SchedulerJobDataTaskId)}
	 * @param schedulerJobDataTask
	 * @return
	 */
	public abstract SchedulerJobDataTaskDO schedulerJobDataTaskToSchedulerJobDataTaskDO(SchedulerJobDataTask schedulerJobDataTask);

}
