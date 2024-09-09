package com.particle.scheduler.client.schedule.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.schedule.dto.command.representation.SchedulerExecuteRecordPageQueryCommand;
import com.particle.scheduler.client.schedule.dto.command.representation.SchedulerExecuteRecordQueryListCommand;
import com.particle.scheduler.client.schedule.dto.data.SchedulerExecuteRecordVO;

/**
 * <p>
 * 任务计划执行记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ISchedulerExecuteRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerExecuteRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<SchedulerExecuteRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param schedulerExecuteRecordQueryListCommand
	 * @return
	 */
	MultiResponse<SchedulerExecuteRecordVO> queryList(SchedulerExecuteRecordQueryListCommand schedulerExecuteRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param schedulerExecuteRecordPageQueryCommand
	 * @return
	 */
	PageResponse<SchedulerExecuteRecordVO> pageQuery(SchedulerExecuteRecordPageQueryCommand schedulerExecuteRecordPageQueryCommand);

}
