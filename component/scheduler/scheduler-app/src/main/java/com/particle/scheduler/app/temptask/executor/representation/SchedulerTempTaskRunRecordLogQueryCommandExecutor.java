package com.particle.scheduler.app.temptask.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskRunRecordLogAppStructMapping;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordLogPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordLogQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordLogVO;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordLogDO;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 任务计划临时任务运行记录日志 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Component
@Validated
public class SchedulerTempTaskRunRecordLogQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ISchedulerTempTaskRunRecordLogService iSchedulerTempTaskRunRecordLogService;

	/**
	 * 执行 任务计划临时任务运行记录日志 列表查询指令
	 * @param schedulerTempTaskRunRecordLogQueryListCommand
	 * @return
	 */
	public MultiResponse<SchedulerTempTaskRunRecordLogVO> execute(@Valid SchedulerTempTaskRunRecordLogQueryListCommand schedulerTempTaskRunRecordLogQueryListCommand) {
		List<SchedulerTempTaskRunRecordLogDO> schedulerTempTaskRunRecordLogDO = iSchedulerTempTaskRunRecordLogService.list(schedulerTempTaskRunRecordLogQueryListCommand);
		List<SchedulerTempTaskRunRecordLogVO> schedulerTempTaskRunRecordLogVOs = SchedulerTempTaskRunRecordLogAppStructMapping.instance.schedulerTempTaskRunRecordLogDOsToSchedulerTempTaskRunRecordLogVOs(schedulerTempTaskRunRecordLogDO);
		return MultiResponse.of(schedulerTempTaskRunRecordLogVOs);
	}
	/**
	 * 执行 任务计划临时任务运行记录日志 分页查询指令
	 * @param schedulerTempTaskRunRecordLogPageQueryCommand
	 * @return
	 */
	public PageResponse<SchedulerTempTaskRunRecordLogVO> execute(@Valid SchedulerTempTaskRunRecordLogPageQueryCommand schedulerTempTaskRunRecordLogPageQueryCommand) {
		Page<SchedulerTempTaskRunRecordLogDO> page = iSchedulerTempTaskRunRecordLogService.listPage(schedulerTempTaskRunRecordLogPageQueryCommand);
		return SchedulerTempTaskRunRecordLogAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 任务计划临时任务运行记录日志 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskRunRecordLogVO> executeDetail(IdCommand detailCommand) {
		SchedulerTempTaskRunRecordLogDO byId = iSchedulerTempTaskRunRecordLogService.getById(detailCommand.getId());
		SchedulerTempTaskRunRecordLogVO schedulerTempTaskRunRecordLogVO = SchedulerTempTaskRunRecordLogAppStructMapping.instance.schedulerTempTaskRunRecordLogDOToSchedulerTempTaskRunRecordLogVO(byId);
		return SingleResponse.of(schedulerTempTaskRunRecordLogVO);
	}
	/**
	 * 执行 任务计划临时任务运行记录日志 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskRunRecordLogVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		SchedulerTempTaskRunRecordLogDO byId = iSchedulerTempTaskRunRecordLogService.getById(detailForUpdateCommand.getId());
		SchedulerTempTaskRunRecordLogVO schedulerTempTaskRunRecordLogVO = SchedulerTempTaskRunRecordLogAppStructMapping.instance.schedulerTempTaskRunRecordLogDOToSchedulerTempTaskRunRecordLogVO(byId);
		return SingleResponse.of(schedulerTempTaskRunRecordLogVO);
	}


	@Autowired
	public void setISchedulerTempTaskRunRecordLogService(ISchedulerTempTaskRunRecordLogService iSchedulerTempTaskRunRecordLogService) {
		this.iSchedulerTempTaskRunRecordLogService = iSchedulerTempTaskRunRecordLogService;
	}
}
