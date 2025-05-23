package com.particle.scheduler.client.datatask.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerJobDataTaskPageQueryCommand;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerJobDataTaskQueryListCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;

/**
 * <p>
 * 任务计划任务数据 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ISchedulerJobDataTaskRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerJobDataTaskVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<SchedulerJobDataTaskVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param schedulerJobDataTaskQueryListCommand
	 * @return
	 */
	MultiResponse<SchedulerJobDataTaskVO> queryList(SchedulerJobDataTaskQueryListCommand schedulerJobDataTaskQueryListCommand);

	/**
	 * 分页查询
	 * @param schedulerJobDataTaskPageQueryCommand
	 * @return
	 */
	PageResponse<SchedulerJobDataTaskVO> pageQuery(SchedulerJobDataTaskPageQueryCommand schedulerJobDataTaskPageQueryCommand);

}
