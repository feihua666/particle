package com.particle.scheduler.app.datatask.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskControlVO;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTask;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTaskId;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerAsyncDataTaskDO;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerAsyncDataTaskPageQueryCommand;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerAsyncDataTaskQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 任务计划异步任务数据 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerAsyncDataTaskAppStructMapping  implements IBaseQueryCommandMapStruct<SchedulerAsyncDataTaskDO>{
	public static SchedulerAsyncDataTaskAppStructMapping instance = Mappers.getMapper( SchedulerAsyncDataTaskAppStructMapping.class );

	protected Long map(SchedulerAsyncDataTaskId schedulerAsyncDataTaskId){
		if (schedulerAsyncDataTaskId == null) {
			return null;
		}
		return schedulerAsyncDataTaskId.getId();
	}
	/**
	 * 任务计划异步任务数据领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerAsyncDataTaskAppStructMapping#map(SchedulerAsyncDataTaskId)}
	 * @param schedulerAsyncDataTask
	 * @return
	 */
	public abstract SchedulerAsyncDataTaskVO toSchedulerAsyncDataTaskVO(SchedulerAsyncDataTask schedulerAsyncDataTask);


	/**
	 * 数据对象转视图对象
	 * @param schedulerAsyncDataTaskDO
	 * @return
	 */
	public abstract SchedulerAsyncDataTaskVO schedulerAsyncDataTaskDOToSchedulerAsyncDataTaskVO(SchedulerAsyncDataTaskDO schedulerAsyncDataTaskDO);

	/**
	 * 批量转换
	 * @param schedulerAsyncDataTaskDOs
	 * @return
	 */
	public abstract List<SchedulerAsyncDataTaskVO> schedulerAsyncDataTaskDOsToSchedulerAsyncDataTaskVOs(List<SchedulerAsyncDataTaskDO> schedulerAsyncDataTaskDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<SchedulerAsyncDataTaskVO> infrastructurePageToPageResponse(Page<SchedulerAsyncDataTaskDO> page) {
		return PageResponse.of(schedulerAsyncDataTaskDOsToSchedulerAsyncDataTaskVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public SchedulerAsyncDataTaskDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof SchedulerAsyncDataTaskPageQueryCommand) {
			return pageQueryCommandToDO((SchedulerAsyncDataTaskPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof SchedulerAsyncDataTaskQueryListCommand) {
			return queryListCommandToDO(((SchedulerAsyncDataTaskQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract SchedulerAsyncDataTaskDO pageQueryCommandToDO(SchedulerAsyncDataTaskPageQueryCommand schedulerAsyncDataTaskPageQueryCommand);

	public abstract SchedulerAsyncDataTaskDO queryListCommandToDO(SchedulerAsyncDataTaskQueryListCommand schedulerAsyncDataTaskQueryListCommand);

	public abstract SchedulerAsyncDataTaskControlVO schedulerAsyncDataTaskVOToSchedulerAsyncDataTaskControlVO(SchedulerAsyncDataTaskVO schedulerAsyncDataTaskVO);
}
