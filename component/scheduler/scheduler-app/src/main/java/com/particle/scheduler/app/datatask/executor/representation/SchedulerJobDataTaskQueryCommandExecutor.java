package com.particle.scheduler.app.datatask.executor.representation;

import com.particle.scheduler.app.datatask.structmapping.SchedulerJobDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerJobDataTaskQueryListCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerJobDataTaskDO;
import com.particle.scheduler.infrastructure.datatask.service.ISchedulerJobDataTaskService;
import com.particle.scheduler.client.datatask.dto.command.representation.SchedulerJobDataTaskPageQueryCommand;
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
 * 任务计划任务数据 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Component
@Validated
public class SchedulerJobDataTaskQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ISchedulerJobDataTaskService iSchedulerJobDataTaskService;

	/**
	 * 执行 任务计划任务数据 列表查询指令
	 * @param schedulerJobDataTaskQueryListCommand
	 * @return
	 */
	public MultiResponse<SchedulerJobDataTaskVO> execute(@Valid SchedulerJobDataTaskQueryListCommand schedulerJobDataTaskQueryListCommand) {
		List<SchedulerJobDataTaskDO> schedulerJobDataTaskDO = iSchedulerJobDataTaskService.list(schedulerJobDataTaskQueryListCommand);
		List<SchedulerJobDataTaskVO> schedulerJobDataTaskVOs = SchedulerJobDataTaskAppStructMapping.instance.schedulerJobDataTaskDOsToSchedulerJobDataTaskVOs(schedulerJobDataTaskDO);
		return MultiResponse.of(schedulerJobDataTaskVOs);
	}
	/**
	 * 执行 任务计划任务数据 分页查询指令
	 * @param schedulerJobDataTaskPageQueryCommand
	 * @return
	 */
	public PageResponse<SchedulerJobDataTaskVO> execute(@Valid SchedulerJobDataTaskPageQueryCommand schedulerJobDataTaskPageQueryCommand) {
		Page<SchedulerJobDataTaskDO> page = iSchedulerJobDataTaskService.listPage(schedulerJobDataTaskPageQueryCommand);
		return SchedulerJobDataTaskAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 任务计划任务数据 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<SchedulerJobDataTaskVO> executeDetail(IdCommand detailCommand) {
		SchedulerJobDataTaskDO byId = iSchedulerJobDataTaskService.getById(detailCommand.getId());
		SchedulerJobDataTaskVO schedulerJobDataTaskVO = SchedulerJobDataTaskAppStructMapping.instance.schedulerJobDataTaskDOToSchedulerJobDataTaskVO(byId);
		return SingleResponse.of(schedulerJobDataTaskVO);
	}
	/**
	 * 执行 任务计划任务数据 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerJobDataTaskVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		SchedulerJobDataTaskDO byId = iSchedulerJobDataTaskService.getById(detailForUpdateCommand.getId());
		SchedulerJobDataTaskVO schedulerJobDataTaskVO = SchedulerJobDataTaskAppStructMapping.instance.schedulerJobDataTaskDOToSchedulerJobDataTaskVO(byId);
		return SingleResponse.of(schedulerJobDataTaskVO);
	}


	@Autowired
	public void setISchedulerJobDataTaskService(ISchedulerJobDataTaskService iSchedulerJobDataTaskService) {
		this.iSchedulerJobDataTaskService = iSchedulerJobDataTaskService;
	}
}
