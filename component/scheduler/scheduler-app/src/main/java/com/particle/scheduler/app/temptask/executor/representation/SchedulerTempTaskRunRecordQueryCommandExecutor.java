package com.particle.scheduler.app.temptask.executor.representation;

import com.particle.scheduler.app.temptask.structmapping.SchedulerTempTaskRunRecordAppStructMapping;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordService;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordPageQueryCommand;
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
 * 任务计划临时任务运行记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Component
@Validated
public class SchedulerTempTaskRunRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService;

	/**
	 * 执行 任务计划临时任务运行记录 列表查询指令
	 * @param schedulerTempTaskRunRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<SchedulerTempTaskRunRecordVO> execute(@Valid SchedulerTempTaskRunRecordQueryListCommand schedulerTempTaskRunRecordQueryListCommand) {
		List<SchedulerTempTaskRunRecordDO> schedulerTempTaskRunRecordDO = iSchedulerTempTaskRunRecordService.list(schedulerTempTaskRunRecordQueryListCommand);
		List<SchedulerTempTaskRunRecordVO> schedulerTempTaskRunRecordVOs = SchedulerTempTaskRunRecordAppStructMapping.instance.schedulerTempTaskRunRecordDOsToSchedulerTempTaskRunRecordVOs(schedulerTempTaskRunRecordDO);
		return MultiResponse.of(schedulerTempTaskRunRecordVOs);
	}
	/**
	 * 执行 任务计划临时任务运行记录 分页查询指令
	 * @param schedulerTempTaskRunRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<SchedulerTempTaskRunRecordVO> execute(@Valid SchedulerTempTaskRunRecordPageQueryCommand schedulerTempTaskRunRecordPageQueryCommand) {
		Page<SchedulerTempTaskRunRecordDO> page = iSchedulerTempTaskRunRecordService.listPage(schedulerTempTaskRunRecordPageQueryCommand);
		return SchedulerTempTaskRunRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 任务计划临时任务运行记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskRunRecordVO> executeDetail(IdCommand detailCommand) {
		SchedulerTempTaskRunRecordDO byId = iSchedulerTempTaskRunRecordService.getById(detailCommand.getId());
		SchedulerTempTaskRunRecordVO schedulerTempTaskRunRecordVO = SchedulerTempTaskRunRecordAppStructMapping.instance.schedulerTempTaskRunRecordDOToSchedulerTempTaskRunRecordVO(byId);
		return SingleResponse.of(schedulerTempTaskRunRecordVO);
	}
	/**
	 * 执行 任务计划临时任务运行记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<SchedulerTempTaskRunRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		SchedulerTempTaskRunRecordDO byId = iSchedulerTempTaskRunRecordService.getById(detailForUpdateCommand.getId());
		SchedulerTempTaskRunRecordVO schedulerTempTaskRunRecordVO = SchedulerTempTaskRunRecordAppStructMapping.instance.schedulerTempTaskRunRecordDOToSchedulerTempTaskRunRecordVO(byId);
		return SingleResponse.of(schedulerTempTaskRunRecordVO);
	}


	@Autowired
	public void setISchedulerTempTaskRunRecordService(ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService) {
		this.iSchedulerTempTaskRunRecordService = iSchedulerTempTaskRunRecordService;
	}
}
