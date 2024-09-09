package com.particle.scheduler.client.temptask.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskCreateCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskUpdateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
/**
 * <p>
 * 任务计划临时任务 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
public interface ISchedulerTempTaskApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param schedulerTempTaskCreateCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskVO> create(SchedulerTempTaskCreateCommand schedulerTempTaskCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param schedulerTempTaskUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskVO> update(SchedulerTempTaskUpdateCommand schedulerTempTaskUpdateCommand);
}
