package com.particle.scheduler.app.temptask.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTask;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskId;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskDO;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 任务计划临时任务 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerTempTaskAppStructMapping  implements IBaseQueryCommandMapStruct<SchedulerTempTaskDO>{
	public static SchedulerTempTaskAppStructMapping instance = Mappers.getMapper( SchedulerTempTaskAppStructMapping.class );

	protected Long map(SchedulerTempTaskId schedulerTempTaskId){
		if (schedulerTempTaskId == null) {
			return null;
		}
		return schedulerTempTaskId.getId();
	}
	/**
	 * 任务计划临时任务领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerTempTaskAppStructMapping#map(SchedulerTempTaskId)}
	 * @param schedulerTempTask
	 * @return
	 */
	public abstract SchedulerTempTaskVO toSchedulerTempTaskVO(SchedulerTempTask schedulerTempTask);


	/**
	 * 数据对象转视图对象
	 * @param schedulerTempTaskDO
	 * @return
	 */
	public abstract SchedulerTempTaskVO schedulerTempTaskDOToSchedulerTempTaskVO(SchedulerTempTaskDO schedulerTempTaskDO);

	/**
	 * 批量转换
	 * @param schedulerTempTaskDOs
	 * @return
	 */
	public abstract List<SchedulerTempTaskVO> schedulerTempTaskDOsToSchedulerTempTaskVOs(List<SchedulerTempTaskDO> schedulerTempTaskDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<SchedulerTempTaskVO> infrastructurePageToPageResponse(Page<SchedulerTempTaskDO> page) {
		return PageResponse.of(schedulerTempTaskDOsToSchedulerTempTaskVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public SchedulerTempTaskDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof SchedulerTempTaskPageQueryCommand) {
			return pageQueryCommandToDO((SchedulerTempTaskPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof SchedulerTempTaskQueryListCommand) {
			return queryListCommandToDO(((SchedulerTempTaskQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract SchedulerTempTaskDO pageQueryCommandToDO(SchedulerTempTaskPageQueryCommand schedulerTempTaskPageQueryCommand);

	public abstract SchedulerTempTaskDO queryListCommandToDO(SchedulerTempTaskQueryListCommand schedulerTempTaskQueryListCommand);
}
