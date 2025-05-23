package com.particle.scheduler.client.datatask.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.scheduler.client.datatask.dto.command.SchedulerJobDataTaskCreateCommand;
import com.particle.scheduler.client.datatask.dto.command.SchedulerJobDataTaskUpdateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;
/**
 * <p>
 * 任务计划任务数据 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
public interface ISchedulerJobDataTaskApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param schedulerJobDataTaskCreateCommand
	 * @return
	 */
	SingleResponse<SchedulerJobDataTaskVO> create(SchedulerJobDataTaskCreateCommand schedulerJobDataTaskCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<SchedulerJobDataTaskVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param schedulerJobDataTaskUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerJobDataTaskVO> update(SchedulerJobDataTaskUpdateCommand schedulerJobDataTaskUpdateCommand);
}
