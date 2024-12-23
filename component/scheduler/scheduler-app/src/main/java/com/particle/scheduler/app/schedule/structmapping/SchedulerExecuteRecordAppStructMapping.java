package com.particle.scheduler.app.schedule.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.scheduler.client.schedule.dto.command.representation.SchedulerExecuteRecordPageQueryCommand;
import com.particle.scheduler.client.schedule.dto.command.representation.SchedulerExecuteRecordQueryListCommand;
import com.particle.scheduler.client.schedule.dto.data.SchedulerExecuteRecordVO;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecord;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecordId;
import com.particle.scheduler.infrastructure.schedule.dos.SchedulerExecuteRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 任务计划执行记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerExecuteRecordAppStructMapping  implements IBaseQueryCommandMapStruct<SchedulerExecuteRecordDO>{
	public static SchedulerExecuteRecordAppStructMapping instance = Mappers.getMapper( SchedulerExecuteRecordAppStructMapping.class );

	protected Long map(SchedulerExecuteRecordId schedulerExecuteRecordId){
		if (schedulerExecuteRecordId == null) {
			return null;
		}
		return schedulerExecuteRecordId.getId();
	}
	/**
	 * 任务计划执行记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerExecuteRecordAppStructMapping#map(SchedulerExecuteRecordId)}
	 * @param schedulerExecuteRecord
	 * @return
	 */
	public abstract SchedulerExecuteRecordVO toSchedulerExecuteRecordVO(SchedulerExecuteRecord schedulerExecuteRecord);


	/**
	 * 数据对象转视图对象
	 * @param schedulerExecuteRecordDO
	 * @return
	 */
	public abstract SchedulerExecuteRecordVO schedulerExecuteRecordDOToSchedulerExecuteRecordVO(SchedulerExecuteRecordDO schedulerExecuteRecordDO);

	/**
	 * 批量转换
	 * @param schedulerExecuteRecordDOs
	 * @return
	 */
	public abstract List<SchedulerExecuteRecordVO> schedulerExecuteRecordDOsToSchedulerExecuteRecordVOs(List<SchedulerExecuteRecordDO> schedulerExecuteRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<SchedulerExecuteRecordVO> infrastructurePageToPageResponse(Page<SchedulerExecuteRecordDO> page) {
		return PageResponse.of(schedulerExecuteRecordDOsToSchedulerExecuteRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public SchedulerExecuteRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof SchedulerExecuteRecordPageQueryCommand) {
			return pageQueryCommandToDO((SchedulerExecuteRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof SchedulerExecuteRecordQueryListCommand) {
			return queryListCommandToDO(((SchedulerExecuteRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract SchedulerExecuteRecordDO pageQueryCommandToDO(SchedulerExecuteRecordPageQueryCommand schedulerExecuteRecordPageQueryCommand);

	public abstract SchedulerExecuteRecordDO queryListCommandToDO(SchedulerExecuteRecordQueryListCommand schedulerExecuteRecordQueryListCommand);
}
