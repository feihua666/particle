package com.particle.scheduler.app.datatask.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerJobDataTaskPageQueryCommand;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerJobDataTaskQueryListCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskControlVO;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTask;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTaskId;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerJobDataTaskDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 任务计划任务数据 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerJobDataTaskAppStructMapping  implements IBaseQueryCommandMapStruct<SchedulerJobDataTaskDO>{
	public static SchedulerJobDataTaskAppStructMapping instance = Mappers.getMapper( SchedulerJobDataTaskAppStructMapping.class );

	protected Long map(SchedulerJobDataTaskId schedulerJobDataTaskId){
		if (schedulerJobDataTaskId == null) {
			return null;
		}
		return schedulerJobDataTaskId.getId();
	}
	/**
	 * 任务计划任务数据领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerJobDataTaskAppStructMapping#map(SchedulerJobDataTaskId)}
	 * @param schedulerJobDataTask
	 * @return
	 */
	public abstract SchedulerJobDataTaskVO toSchedulerJobDataTaskVO(SchedulerJobDataTask schedulerJobDataTask);


	/**
	 * 数据对象转视图对象
	 * @param schedulerJobDataTaskDO
	 * @return
	 */
	public abstract SchedulerJobDataTaskVO schedulerJobDataTaskDOToSchedulerJobDataTaskVO(SchedulerJobDataTaskDO schedulerJobDataTaskDO);

	/**
	 * 批量转换
	 * @param schedulerJobDataTaskDOs
	 * @return
	 */
	public abstract List<SchedulerJobDataTaskVO> schedulerJobDataTaskDOsToSchedulerJobDataTaskVOs(List<SchedulerJobDataTaskDO> schedulerJobDataTaskDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<SchedulerJobDataTaskVO> infrastructurePageToPageResponse(Page<SchedulerJobDataTaskDO> page) {
		return PageResponse.of(schedulerJobDataTaskDOsToSchedulerJobDataTaskVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public SchedulerJobDataTaskDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof SchedulerJobDataTaskPageQueryCommand) {
			return pageQueryCommandToDO((SchedulerJobDataTaskPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof SchedulerJobDataTaskQueryListCommand) {
			return queryListCommandToDO(((SchedulerJobDataTaskQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract SchedulerJobDataTaskDO pageQueryCommandToDO(SchedulerJobDataTaskPageQueryCommand schedulerJobDataTaskPageQueryCommand);

	public abstract SchedulerJobDataTaskDO queryListCommandToDO(SchedulerJobDataTaskQueryListCommand schedulerJobDataTaskQueryListCommand);

	public abstract SchedulerJobDataTaskControlVO schedulerJobDataTaskVOToSchedulerJobDataTaskControlVO(SchedulerJobDataTaskVO schedulerJobDataTaskVO);

}
