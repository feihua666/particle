package com.particle.scheduler.app.temptask.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordLogPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordLogQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordLogVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLog;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLogId;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 任务计划临时任务运行记录日志 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerTempTaskRunRecordLogAppStructMapping  implements IBaseQueryCommandMapStruct<SchedulerTempTaskRunRecordLogDO>{
	public static SchedulerTempTaskRunRecordLogAppStructMapping instance = Mappers.getMapper( SchedulerTempTaskRunRecordLogAppStructMapping.class );

	protected Long map(SchedulerTempTaskRunRecordLogId schedulerTempTaskRunRecordLogId){
		if (schedulerTempTaskRunRecordLogId == null) {
			return null;
		}
		return schedulerTempTaskRunRecordLogId.getId();
	}
	/**
	 * 任务计划临时任务运行记录日志领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerTempTaskRunRecordLogAppStructMapping#map(SchedulerTempTaskRunRecordLogId)}
	 * @param schedulerTempTaskRunRecordLog
	 * @return
	 */
	public abstract SchedulerTempTaskRunRecordLogVO toSchedulerTempTaskRunRecordLogVO(SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog);


	/**
	 * 数据对象转视图对象
	 * @param schedulerTempTaskRunRecordLogDO
	 * @return
	 */
	public abstract SchedulerTempTaskRunRecordLogVO schedulerTempTaskRunRecordLogDOToSchedulerTempTaskRunRecordLogVO(SchedulerTempTaskRunRecordLogDO schedulerTempTaskRunRecordLogDO);

	/**
	 * 批量转换
	 * @param schedulerTempTaskRunRecordLogDOs
	 * @return
	 */
	public abstract List<SchedulerTempTaskRunRecordLogVO> schedulerTempTaskRunRecordLogDOsToSchedulerTempTaskRunRecordLogVOs(List<SchedulerTempTaskRunRecordLogDO> schedulerTempTaskRunRecordLogDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<SchedulerTempTaskRunRecordLogVO> infrastructurePageToPageResponse(Page<SchedulerTempTaskRunRecordLogDO> page) {
		return PageResponse.of(schedulerTempTaskRunRecordLogDOsToSchedulerTempTaskRunRecordLogVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public SchedulerTempTaskRunRecordLogDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof SchedulerTempTaskRunRecordLogPageQueryCommand) {
			return pageQueryCommandToDO((SchedulerTempTaskRunRecordLogPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof SchedulerTempTaskRunRecordLogQueryListCommand) {
			return queryListCommandToDO(((SchedulerTempTaskRunRecordLogQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract SchedulerTempTaskRunRecordLogDO pageQueryCommandToDO(SchedulerTempTaskRunRecordLogPageQueryCommand schedulerTempTaskRunRecordLogPageQueryCommand);

	public abstract SchedulerTempTaskRunRecordLogDO queryListCommandToDO(SchedulerTempTaskRunRecordLogQueryListCommand schedulerTempTaskRunRecordLogQueryListCommand);
}
