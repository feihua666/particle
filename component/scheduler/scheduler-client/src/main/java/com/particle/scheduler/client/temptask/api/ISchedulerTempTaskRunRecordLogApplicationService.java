package com.particle.scheduler.client.temptask.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordLogCreateCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordLogUpdateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordLogVO;
/**
 * <p>
 * 任务计划临时任务运行记录日志 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
public interface ISchedulerTempTaskRunRecordLogApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param schedulerTempTaskRunRecordLogCreateCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskRunRecordLogVO> create(SchedulerTempTaskRunRecordLogCreateCommand schedulerTempTaskRunRecordLogCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskRunRecordLogVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param schedulerTempTaskRunRecordLogUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskRunRecordLogVO> update(SchedulerTempTaskRunRecordLogUpdateCommand schedulerTempTaskRunRecordLogUpdateCommand);
}
