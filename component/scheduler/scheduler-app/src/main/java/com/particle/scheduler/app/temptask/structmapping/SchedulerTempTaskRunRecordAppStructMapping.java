package com.particle.scheduler.app.temptask.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecord;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordId;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 任务计划临时任务运行记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SchedulerTempTaskRunRecordAppStructMapping  implements IBaseQueryCommandMapStruct<SchedulerTempTaskRunRecordDO>{
	public static SchedulerTempTaskRunRecordAppStructMapping instance = Mappers.getMapper( SchedulerTempTaskRunRecordAppStructMapping.class );

	protected Long map(SchedulerTempTaskRunRecordId schedulerTempTaskRunRecordId){
		if (schedulerTempTaskRunRecordId == null) {
			return null;
		}
		return schedulerTempTaskRunRecordId.getId();
	}
	/**
	 * 任务计划临时任务运行记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SchedulerTempTaskRunRecordAppStructMapping#map(SchedulerTempTaskRunRecordId)}
	 * @param schedulerTempTaskRunRecord
	 * @return
	 */
	public abstract SchedulerTempTaskRunRecordVO toSchedulerTempTaskRunRecordVO(SchedulerTempTaskRunRecord schedulerTempTaskRunRecord);


	/**
	 * 数据对象转视图对象
	 * @param schedulerTempTaskRunRecordDO
	 * @return
	 */
	public abstract SchedulerTempTaskRunRecordVO schedulerTempTaskRunRecordDOToSchedulerTempTaskRunRecordVO(SchedulerTempTaskRunRecordDO schedulerTempTaskRunRecordDO);

	/**
	 * 批量转换
	 * @param schedulerTempTaskRunRecordDOs
	 * @return
	 */
	public abstract List<SchedulerTempTaskRunRecordVO> schedulerTempTaskRunRecordDOsToSchedulerTempTaskRunRecordVOs(List<SchedulerTempTaskRunRecordDO> schedulerTempTaskRunRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<SchedulerTempTaskRunRecordVO> infrastructurePageToPageResponse(Page<SchedulerTempTaskRunRecordDO> page) {
		return PageResponse.of(schedulerTempTaskRunRecordDOsToSchedulerTempTaskRunRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public SchedulerTempTaskRunRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof SchedulerTempTaskRunRecordPageQueryCommand) {
			return pageQueryCommandToDO((SchedulerTempTaskRunRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof SchedulerTempTaskRunRecordQueryListCommand) {
			return queryListCommandToDO(((SchedulerTempTaskRunRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract SchedulerTempTaskRunRecordDO pageQueryCommandToDO(SchedulerTempTaskRunRecordPageQueryCommand schedulerTempTaskRunRecordPageQueryCommand);

	public abstract SchedulerTempTaskRunRecordDO queryListCommandToDO(SchedulerTempTaskRunRecordQueryListCommand schedulerTempTaskRunRecordQueryListCommand);
}
