package com.particle.scheduler.client.temptask.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;

/**
 * <p>
 * 任务计划临时任务 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ISchedulerTempTaskRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<SchedulerTempTaskVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param schedulerTempTaskQueryListCommand
	 * @return
	 */
	MultiResponse<SchedulerTempTaskVO> queryList(SchedulerTempTaskQueryListCommand schedulerTempTaskQueryListCommand);

	/**
	 * 分页查询
	 * @param schedulerTempTaskPageQueryCommand
	 * @return
	 */
	PageResponse<SchedulerTempTaskVO> pageQuery(SchedulerTempTaskPageQueryCommand schedulerTempTaskPageQueryCommand);

}
