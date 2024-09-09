package com.particle.scheduler.app.temptask.executor;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.NetUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.tool.log.TraceTool;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskCreateCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordCreateCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordLogCreateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
import com.particle.scheduler.domain.enums.SchedulerTempTaskRunRecordStatus;
import com.particle.scheduler.domain.gateway.SchedulerDictGateway;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskGateway;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskDO;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordService;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 任务计划临时任务 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Component
@Validated
public class SchedulerTempTaskCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerTempTaskGateway schedulerTempTaskGateway;

	private ISchedulerTempTaskService iSchedulerTempTaskService;

	private SchedulerTempTaskCreateCommandExecutor schedulerTempTaskCreateCommandExecutor;

	private ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService;

	private SchedulerDictGateway schedulerDictGateway;

	private SchedulerTempTaskRunRecordCreateCommandExecutor schedulerTempTaskRunRecordCreateCommandExecutor;

	private SchedulerTempTaskRunRecordLogCreateCommandExecutor schedulerTempTaskRunRecordLogCreateCommandExecutor;

	public Long start(String code, String name) {
		SchedulerTempTaskDO byCode = iSchedulerTempTaskService.getByCode(code);
		Long schedulerTempTaskId = null;
		if (byCode == null) {
			SchedulerTempTaskCreateCommand schedulerTempTaskCreateCommand = new SchedulerTempTaskCreateCommand();
			schedulerTempTaskCreateCommand.setCode(code);
			schedulerTempTaskCreateCommand.setName(name);
			SingleResponse<SchedulerTempTaskVO> execute = schedulerTempTaskCreateCommandExecutor.execute(schedulerTempTaskCreateCommand);
			schedulerTempTaskId = execute.getData().getId();
		}
		// 查找是否存在运行中的任务，如果有，则不允许运行
		Long runningStatusDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerTempTaskRunRecordStatus.running.groupCode(), SchedulerTempTaskRunRecordStatus.running.itemValue());
		List<SchedulerTempTaskRunRecordDO> bySchedulerTempTaskIdAndStatusDictId = iSchedulerTempTaskRunRecordService.getBySchedulerTempTaskIdAndStatusDictId(schedulerTempTaskId, runningStatusDictId);
		Assert.isTrue(CollectionUtil.isEmpty(bySchedulerTempTaskIdAndStatusDictId),"存在运行中的任务，请务重复运行");


		SchedulerTempTaskRunRecordCreateCommand schedulerTempTaskRunRecordCreateCommand = new SchedulerTempTaskRunRecordCreateCommand();
		schedulerTempTaskRunRecordCreateCommand.setSchedulerTempTaskId(schedulerTempTaskId);
		schedulerTempTaskRunRecordCreateCommand.setStartAt(LocalDateTime.now());
		schedulerTempTaskRunRecordCreateCommand.setIsAllowRunSwitch(true);
		schedulerTempTaskRunRecordCreateCommand.setIsHasError(false);
		schedulerTempTaskRunRecordCreateCommand.setLocalHostIp(NetUtil.getLocalhostStr());
		schedulerTempTaskRunRecordCreateCommand.setLocalHostName(NetUtil.getLocalHostName());
		schedulerTempTaskRunRecordCreateCommand.setExecuteStatusDictId(runningStatusDictId);

		try {
			schedulerTempTaskRunRecordCreateCommand.setTraceId(TraceTool.getTraceId());
		} catch (Exception e) {
		}
		SingleResponse<SchedulerTempTaskRunRecordVO> execute = schedulerTempTaskRunRecordCreateCommandExecutor.execute(schedulerTempTaskRunRecordCreateCommand);
		return execute.getData().getId();
	}

	public void finish(Long id, Boolean isHasError,String result) {
		SchedulerTempTaskRunRecordDO schedulerTempTaskRunRecordDO = iSchedulerTempTaskRunRecordService.getById(id);
		if (schedulerTempTaskRunRecordDO != null) {
			Long finishStatusDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerTempTaskRunRecordStatus.running.groupCode(), SchedulerTempTaskRunRecordStatus.finished.itemValue());
			schedulerTempTaskRunRecordDO.setFinishAt(LocalDateTime.now());
			schedulerTempTaskRunRecordDO.setIsHasError(isHasError);
			schedulerTempTaskRunRecordDO.setExecuteStatusDictId(finishStatusDictId);
			schedulerTempTaskRunRecordDO.setResult(result);
			iSchedulerTempTaskRunRecordService.updateById(schedulerTempTaskRunRecordDO);
		}

	}

	public void log(String level, Long id, String message) {
		SchedulerTempTaskRunRecordLogCreateCommand schedulerTempTaskRunRecordLogCreateCommand = new SchedulerTempTaskRunRecordLogCreateCommand();
		schedulerTempTaskRunRecordLogCreateCommand.setSchedulerTempTaskRunRecordId(id);
		schedulerTempTaskRunRecordLogCreateCommand.setLevel(level);
		schedulerTempTaskRunRecordLogCreateCommand.setContent(message);
		schedulerTempTaskRunRecordLogCreateCommandExecutor.execute(schedulerTempTaskRunRecordLogCreateCommand);
	}

	public boolean checkIsAllowRunSwitch(Long id) {
		SchedulerTempTaskRunRecordDO schedulerTempTaskRunRecordDO = iSchedulerTempTaskRunRecordService.getById(id);
		if (schedulerTempTaskRunRecordDO == null) {
			return false;
		}
		return schedulerTempTaskRunRecordDO.getIsAllowRunSwitch();
	}
	/**
	 * 注入使用set方法
	 * @param schedulerTempTaskGateway
	 */
	@Autowired
	public void setSchedulerTempTaskGateway(SchedulerTempTaskGateway schedulerTempTaskGateway) {
		this.schedulerTempTaskGateway = schedulerTempTaskGateway;
	}
	@Autowired
	public void setISchedulerTempTaskService(ISchedulerTempTaskService iSchedulerTempTaskService) {
		this.iSchedulerTempTaskService = iSchedulerTempTaskService;
	}
	@Autowired
	public void setSchedulerTempTaskCreateCommandExecutor(SchedulerTempTaskCreateCommandExecutor schedulerTempTaskCreateCommandExecutor) {
		this.schedulerTempTaskCreateCommandExecutor = schedulerTempTaskCreateCommandExecutor;
	}

	@Autowired
	public void setiSchedulerTempTaskRunRecordService(ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService) {
		this.iSchedulerTempTaskRunRecordService = iSchedulerTempTaskRunRecordService;
	}

	@Autowired
	public void setSchedulerDictGateway(SchedulerDictGateway schedulerDictGateway) {
		this.schedulerDictGateway = schedulerDictGateway;
	}

	@Autowired
	public void setSchedulerTempTaskRunRecordCreateCommandExecutor(SchedulerTempTaskRunRecordCreateCommandExecutor schedulerTempTaskRunRecordCreateCommandExecutor) {
		this.schedulerTempTaskRunRecordCreateCommandExecutor = schedulerTempTaskRunRecordCreateCommandExecutor;
	}


	@Autowired
	public void setSchedulerTempTaskRunRecordLogCreateCommandExecutor(SchedulerTempTaskRunRecordLogCreateCommandExecutor schedulerTempTaskRunRecordLogCreateCommandExecutor) {
		this.schedulerTempTaskRunRecordLogCreateCommandExecutor = schedulerTempTaskRunRecordLogCreateCommandExecutor;
	}
}
