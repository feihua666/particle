package com.particle.scheduler.client.datatask.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.scheduler.client.datatask.dto.command.SchedulerAsyncDataTaskCreateCommand;
import com.particle.scheduler.client.datatask.dto.command.SchedulerAsyncDataTaskUpdateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;
/**
 * <p>
 * 任务计划异步任务数据 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
public interface ISchedulerAsyncDataTaskApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param schedulerAsyncDataTaskCreateCommand
	 * @return
	 */
	SingleResponse<SchedulerAsyncDataTaskVO> create(SchedulerAsyncDataTaskCreateCommand schedulerAsyncDataTaskCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<SchedulerAsyncDataTaskVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param schedulerAsyncDataTaskUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerAsyncDataTaskVO> update(SchedulerAsyncDataTaskUpdateCommand schedulerAsyncDataTaskUpdateCommand);
}
