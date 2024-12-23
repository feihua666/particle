package com.particle.scheduler.client.temptask.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordCreateCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordUpdateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;
/**
 * <p>
 * 任务计划临时任务运行记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
public interface ISchedulerTempTaskRunRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param schedulerTempTaskRunRecordCreateCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskRunRecordVO> create(SchedulerTempTaskRunRecordCreateCommand schedulerTempTaskRunRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskRunRecordVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param schedulerTempTaskRunRecordUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskRunRecordVO> update(SchedulerTempTaskRunRecordUpdateCommand schedulerTempTaskRunRecordUpdateCommand);
}
