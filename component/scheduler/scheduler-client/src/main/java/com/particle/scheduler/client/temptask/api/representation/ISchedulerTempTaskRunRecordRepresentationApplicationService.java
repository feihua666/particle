package com.particle.scheduler.client.temptask.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;

/**
 * <p>
 * 任务计划临时任务运行记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ISchedulerTempTaskRunRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskRunRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskRunRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param schedulerTempTaskRunRecordQueryListCommand
	 * @return
	 */
	MultiResponse<SchedulerTempTaskRunRecordVO> queryList(SchedulerTempTaskRunRecordQueryListCommand schedulerTempTaskRunRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param schedulerTempTaskRunRecordPageQueryCommand
	 * @return
	 */
	PageResponse<SchedulerTempTaskRunRecordVO> pageQuery(SchedulerTempTaskRunRecordPageQueryCommand schedulerTempTaskRunRecordPageQueryCommand);

}
