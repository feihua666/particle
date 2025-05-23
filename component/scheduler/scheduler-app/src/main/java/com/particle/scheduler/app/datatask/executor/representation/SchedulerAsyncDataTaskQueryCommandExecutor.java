package com.particle.scheduler.app.datatask.executor.representation;

import com.particle.scheduler.app.datatask.structmapping.SchedulerAsyncDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerAsyncDataTaskQueryListCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerAsyncDataTaskDO;
import com.particle.scheduler.infrastructure.datatask.service.ISchedulerAsyncDataTaskService;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerAsyncDataTaskPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 任务计划异步任务数据 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Component
@Validated
public class SchedulerAsyncDataTaskQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ISchedulerAsyncDataTaskService iSchedulerAsyncDataTaskService;

	/**
	 * 执行 任务计划异步任务数据 列表查询指令
	 * @param schedulerAsyncDataTaskQueryListCommand
	 * @return
	 */
	public MultiResponse<SchedulerAsyncDataTaskVO> execute(@Valid SchedulerAsyncDataTaskQueryListCommand schedulerAsyncDataTaskQueryListCommand) {
		List<SchedulerAsyncDataTaskDO> schedulerAsyncDataTaskDO = iSchedulerAsyncDataTaskService.list(schedulerAsyncDataTaskQueryListCommand);
		List<SchedulerAsyncDataTaskVO> schedulerAsyncDataTaskVOs = SchedulerAsyncDataTaskAppStructMapping.instance.schedulerAsyncDataTaskDOsToSchedulerAsyncDataTaskVOs(schedulerAsyncDataTaskDO);
		return MultiResponse.of(schedulerAsyncDataTaskVOs);
	}
	/**
	 * 执行 任务计划异步任务数据 分页查询指令
	 * @param schedulerAsyncDataTaskPageQueryCommand
	 * @return
	 */
	public PageResponse<SchedulerAsyncDataTaskVO> execute(@Valid SchedulerAsyncDataTaskPageQueryCommand schedulerAsyncDataTaskPageQueryCommand) {
		Page<SchedulerAsyncDataTaskDO> page = iSchedulerAsyncDataTaskService.listPage(schedulerAsyncDataTaskPageQueryCommand);
		return SchedulerAsyncDataTaskAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 任务计划异步任务数据 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<SchedulerAsyncDataTaskVO> executeDetail(IdCommand detailCommand) {
		SchedulerAsyncDataTaskDO byId = iSchedulerAsyncDataTaskService.getById(detailCommand.getId());
		SchedulerAsyncDataTaskVO schedulerAsyncDataTaskVO = SchedulerAsyncDataTaskAppStructMapping.instance.schedulerAsyncDataTaskDOToSchedulerAsyncDataTaskVO(byId);
		return SingleResponse.of(schedulerAsyncDataTaskVO);
	}
	/**
	 * 执行 任务计划异步任务数据 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerAsyncDataTaskVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		SchedulerAsyncDataTaskDO byId = iSchedulerAsyncDataTaskService.getById(detailForUpdateCommand.getId());
		SchedulerAsyncDataTaskVO schedulerAsyncDataTaskVO = SchedulerAsyncDataTaskAppStructMapping.instance.schedulerAsyncDataTaskDOToSchedulerAsyncDataTaskVO(byId);
		return SingleResponse.of(schedulerAsyncDataTaskVO);
	}


	@Autowired
	public void setISchedulerAsyncDataTaskService(ISchedulerAsyncDataTaskService iSchedulerAsyncDataTaskService) {
		this.iSchedulerAsyncDataTaskService = iSchedulerAsyncDataTaskService;
	}
}
