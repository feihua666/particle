package com.particle.scheduler.infrastructure.datatask.structmapping;

import com.particle.scheduler.infrastructure.datatask.dos.SchedulerAsyncDataTaskDO;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTask;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTaskId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 任务计划异步任务数据 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerAsyncDataTaskInfrastructureStructMapping {
	public static SchedulerAsyncDataTaskInfrastructureStructMapping instance = Mappers.getMapper( SchedulerAsyncDataTaskInfrastructureStructMapping.class );

	protected SchedulerAsyncDataTaskId map(Long id){
		if (id == null) {
			return null;
		}
		return SchedulerAsyncDataTaskId.of(id);
	}
	protected Long map(SchedulerAsyncDataTaskId schedulerAsyncDataTaskId){
		if (schedulerAsyncDataTaskId == null) {
			return null;
		}
		return schedulerAsyncDataTaskId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerAsyncDataTaskInfrastructureStructMapping#map(java.lang.Long)}
	 * @param schedulerAsyncDataTaskDO
	 * @return
	 */
	public abstract SchedulerAsyncDataTask schedulerAsyncDataTaskDOToSchedulerAsyncDataTask(@MappingTarget SchedulerAsyncDataTask schedulerAsyncDataTask,SchedulerAsyncDataTaskDO schedulerAsyncDataTaskDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerAsyncDataTaskInfrastructureStructMapping#map(SchedulerAsyncDataTaskId)}
	 * @param schedulerAsyncDataTask
	 * @return
	 */
	public abstract SchedulerAsyncDataTaskDO schedulerAsyncDataTaskToSchedulerAsyncDataTaskDO(SchedulerAsyncDataTask schedulerAsyncDataTask);

}
