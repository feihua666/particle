package com.particle.scheduler.client.schedule.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.schedule.dto.command.SchedulerExecuteRecordCreateCommand;
import com.particle.scheduler.client.schedule.dto.command.SchedulerExecuteRecordUpdateCommand;
import com.particle.scheduler.client.schedule.dto.data.SchedulerExecuteRecordVO;
/**
 * <p>
 * 任务计划执行记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
public interface ISchedulerExecuteRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param schedulerExecuteRecordCreateCommand
	 * @return
	 */
	SingleResponse<SchedulerExecuteRecordVO> create(SchedulerExecuteRecordCreateCommand schedulerExecuteRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<SchedulerExecuteRecordVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param schedulerExecuteRecordUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerExecuteRecordVO> update(SchedulerExecuteRecordUpdateCommand schedulerExecuteRecordUpdateCommand);
}
