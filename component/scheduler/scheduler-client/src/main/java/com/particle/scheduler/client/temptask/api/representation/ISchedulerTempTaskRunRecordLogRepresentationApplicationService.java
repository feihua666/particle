package com.particle.scheduler.client.temptask.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordLogPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordLogQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordLogVO;

/**
 * <p>
 * 任务计划临时任务运行记录日志 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ISchedulerTempTaskRunRecordLogRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskRunRecordLogVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskRunRecordLogVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param schedulerTempTaskRunRecordLogQueryListCommand
	 * @return
	 */
	MultiResponse<SchedulerTempTaskRunRecordLogVO> queryList(SchedulerTempTaskRunRecordLogQueryListCommand schedulerTempTaskRunRecordLogQueryListCommand);

	/**
	 * 分页查询
	 * @param schedulerTempTaskRunRecordLogPageQueryCommand
	 * @return
	 */
	PageResponse<SchedulerTempTaskRunRecordLogVO> pageQuery(SchedulerTempTaskRunRecordLogPageQueryCommand schedulerTempTaskRunRecordLogPageQueryCommand);

}
