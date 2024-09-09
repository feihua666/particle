package com.particle.scheduler.app.schedule.executor.representation;

import com.particle.scheduler.app.schedule.structmapping.SchedulerExecuteRecordAppStructMapping;
import com.particle.scheduler.client.schedule.dto.command.representation.SchedulerExecuteRecordQueryListCommand;
import com.particle.scheduler.client.schedule.dto.data.SchedulerExecuteRecordVO;
import com.particle.scheduler.infrastructure.schedule.dos.SchedulerExecuteRecordDO;
import com.particle.scheduler.infrastructure.schedule.service.ISchedulerExecuteRecordService;
import com.particle.scheduler.client.schedule.dto.command.representation.SchedulerExecuteRecordPageQueryCommand;
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
 * 任务计划执行记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Component
@Validated
public class SchedulerExecuteRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ISchedulerExecuteRecordService iSchedulerExecuteRecordService;

	/**
	 * 执行 任务计划执行记录 列表查询指令
	 * @param schedulerExecuteRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<SchedulerExecuteRecordVO> execute(@Valid SchedulerExecuteRecordQueryListCommand schedulerExecuteRecordQueryListCommand) {
		List<SchedulerExecuteRecordDO> schedulerExecuteRecordDO = iSchedulerExecuteRecordService.list(schedulerExecuteRecordQueryListCommand);
		List<SchedulerExecuteRecordVO> schedulerExecuteRecordVOs = SchedulerExecuteRecordAppStructMapping.instance.schedulerExecuteRecordDOsToSchedulerExecuteRecordVOs(schedulerExecuteRecordDO);
		return MultiResponse.of(schedulerExecuteRecordVOs);
	}
	/**
	 * 执行 任务计划执行记录 分页查询指令
	 * @param schedulerExecuteRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<SchedulerExecuteRecordVO> execute(@Valid SchedulerExecuteRecordPageQueryCommand schedulerExecuteRecordPageQueryCommand) {
		Page<SchedulerExecuteRecordDO> page = iSchedulerExecuteRecordService.listPage(schedulerExecuteRecordPageQueryCommand);
		return SchedulerExecuteRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 任务计划执行记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<SchedulerExecuteRecordVO> executeDetail(IdCommand detailCommand) {
		SchedulerExecuteRecordDO byId = iSchedulerExecuteRecordService.getById(detailCommand.getId());
		SchedulerExecuteRecordVO schedulerExecuteRecordVO = SchedulerExecuteRecordAppStructMapping.instance.schedulerExecuteRecordDOToSchedulerExecuteRecordVO(byId);
		return SingleResponse.of(schedulerExecuteRecordVO);
	}
	/**
	 * 执行 任务计划执行记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerExecuteRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		SchedulerExecuteRecordDO byId = iSchedulerExecuteRecordService.getById(detailForUpdateCommand.getId());
		SchedulerExecuteRecordVO schedulerExecuteRecordVO = SchedulerExecuteRecordAppStructMapping.instance.schedulerExecuteRecordDOToSchedulerExecuteRecordVO(byId);
		return SingleResponse.of(schedulerExecuteRecordVO);
	}


	@Autowired
	public void setISchedulerExecuteRecordService(ISchedulerExecuteRecordService iSchedulerExecuteRecordService) {
		this.iSchedulerExecuteRecordService = iSchedulerExecuteRecordService;
	}
}
