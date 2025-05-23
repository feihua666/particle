package com.particle.scheduler.client.datatask.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerAsyncDataTaskPageQueryCommand;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerAsyncDataTaskQueryListCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;

/**
 * <p>
 * 任务计划异步任务数据 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ISchedulerAsyncDataTaskRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerAsyncDataTaskVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<SchedulerAsyncDataTaskVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param schedulerAsyncDataTaskQueryListCommand
	 * @return
	 */
	MultiResponse<SchedulerAsyncDataTaskVO> queryList(SchedulerAsyncDataTaskQueryListCommand schedulerAsyncDataTaskQueryListCommand);

	/**
	 * 分页查询
	 * @param schedulerAsyncDataTaskPageQueryCommand
	 * @return
	 */
	PageResponse<SchedulerAsyncDataTaskVO> pageQuery(SchedulerAsyncDataTaskPageQueryCommand schedulerAsyncDataTaskPageQueryCommand);

}
