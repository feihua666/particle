package com.particle.scheduler.app.temptask.executor.representation;

import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskAppStructMapping;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskDO;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskService;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 任务计划临时任务 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Component
@Validated
public class SchedulerTempTaskQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ISchedulerTempTaskService iSchedulerTempTaskService;

	/**
	 * 执行 任务计划临时任务 列表查询指令
	 * @param schedulerTempTaskQueryListCommand
	 * @return
	 */
	public MultiResponse<SchedulerTempTaskVO> execute(@Valid SchedulerTempTaskQueryListCommand schedulerTempTaskQueryListCommand) {
		List<SchedulerTempTaskDO> schedulerTempTaskDO = iSchedulerTempTaskService.list(schedulerTempTaskQueryListCommand);
		List<SchedulerTempTaskVO> schedulerTempTaskVOs = SchedulerTempTaskAppStructMapping.instance.schedulerTempTaskDOsToSchedulerTempTaskVOs(schedulerTempTaskDO);
		return MultiResponse.of(schedulerTempTaskVOs);
	}
	/**
	 * 执行 任务计划临时任务 分页查询指令
	 * @param schedulerTempTaskPageQueryCommand
	 * @return
	 */
	public PageResponse<SchedulerTempTaskVO> execute(@Valid SchedulerTempTaskPageQueryCommand schedulerTempTaskPageQueryCommand) {
		Page<SchedulerTempTaskDO> page = iSchedulerTempTaskService.listPage(schedulerTempTaskPageQueryCommand);
		return SchedulerTempTaskAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 任务计划临时任务 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskVO> executeDetail(IdCommand detailCommand) {
		SchedulerTempTaskDO byId = iSchedulerTempTaskService.getById(detailCommand.getId());
		SchedulerTempTaskVO schedulerTempTaskVO = SchedulerTempTaskAppStructMapping.instance.schedulerTempTaskDOToSchedulerTempTaskVO(byId);
		return SingleResponse.of(schedulerTempTaskVO);
	}
	/**
	 * 执行 任务计划临时任务 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		SchedulerTempTaskDO byId = iSchedulerTempTaskService.getById(detailForUpdateCommand.getId());
		SchedulerTempTaskVO schedulerTempTaskVO = SchedulerTempTaskAppStructMapping.instance.schedulerTempTaskDOToSchedulerTempTaskVO(byId);
		return SingleResponse.of(schedulerTempTaskVO);
	}


	@Autowired
	public void setISchedulerTempTaskService(ISchedulerTempTaskService iSchedulerTempTaskService) {
		this.iSchedulerTempTaskService = iSchedulerTempTaskService;
	}
}
