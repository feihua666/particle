package com.particle.scheduler.app.datatask.executor;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.tool.log.TraceTool;
import com.particle.scheduler.app.datatask.structmapping.SchedulerAsyncDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.dto.command.SchedulerAsyncDataTaskCreateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskControlVO;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTaskId;
import com.particle.scheduler.domain.datatask.gateway.SchedulerAsyncDataTaskGateway;
import com.particle.scheduler.domain.enums.SchedulerDataTaskStatus;
import com.particle.scheduler.domain.gateway.SchedulerDictGateway;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerAsyncDataTaskDO;
import com.particle.scheduler.infrastructure.datatask.service.ISchedulerAsyncDataTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划异步任务数据 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Component
@Validated
public class SchedulerAsyncDataTaskCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerAsyncDataTaskGateway schedulerAsyncDataTaskGateway;
	private ISchedulerAsyncDataTaskService iSchedulerAsyncDataTaskService;
	private SchedulerDictGateway schedulerDictGateway;
	private SchedulerAsyncDataTaskCreateCommandExecutor schedulerAsyncDataTaskCreateCommandExecutor;


	/**
	 * 提交一个异步任务
	 * @param groupIdentifier
	 * @param uniqueIdentifier
	 * @param params
	 * @param dataExpireAt
	 * @return
	 */
	public SchedulerAsyncDataTaskVO submit(String groupIdentifier, String uniqueIdentifier,String params,LocalDateTime dataExpireAt) {
		SchedulerAsyncDataTaskDO schedulerAsyncDataTaskDO = getDO(null, uniqueIdentifier, false);

		if (schedulerAsyncDataTaskDO != null) {
			Assert.isTrue(schedulerAsyncDataTaskDO == null,StrUtil.format("已存在该任务，请勿重复提交，uniqueIdentifier={}", uniqueIdentifier));
		}

		SchedulerAsyncDataTaskCreateCommand schedulerAsyncDataTaskCreateCommand = new SchedulerAsyncDataTaskCreateCommand();
		schedulerAsyncDataTaskCreateCommand.setGroupIdentifier(groupIdentifier);
		schedulerAsyncDataTaskCreateCommand.setUniqueIdentifier(uniqueIdentifier);
		schedulerAsyncDataTaskCreateCommand.setParams(params);
		Long submitDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerDataTaskStatus.Group.scheduler_data_task_status.groupCode(), SchedulerDataTaskStatus.submited.itemValue());
		schedulerAsyncDataTaskCreateCommand.setExecuteStatusDictId(submitDictId);
		schedulerAsyncDataTaskCreateCommand.setLocalHostIp(NetUtil.getLocalhostStr());
		schedulerAsyncDataTaskCreateCommand.setLocalHostName(NetUtil.getLocalHostName());
		schedulerAsyncDataTaskCreateCommand.setTraceId(TraceTool.getTraceId());
		schedulerAsyncDataTaskCreateCommand.setDataExpireAt(dataExpireAt);

		SingleResponse<SchedulerAsyncDataTaskVO> execute = schedulerAsyncDataTaskCreateCommandExecutor.execute(schedulerAsyncDataTaskCreateCommand);
		return execute.getData();
	}

	/**
	 * 处理开始一个异步任务
	 * @param id
	 * @param uniqueIdentifier
	 */
	public void processStart(Long id, String uniqueIdentifier) {
		SchedulerAsyncDataTaskDO schedulerAsyncDataTaskDO = getDO(id, uniqueIdentifier, true);
		Long processDictId = null;
		processDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerDataTaskStatus.Group.scheduler_data_task_status.groupCode(), SchedulerDataTaskStatus.processing.itemValue());
		schedulerAsyncDataTaskDO.setExecuteStatusDictId(processDictId);
		schedulerAsyncDataTaskDO.setStartAt(LocalDateTime.now());
		iSchedulerAsyncDataTaskService.updateById(schedulerAsyncDataTaskDO);
	}

	/**
	 * 完成一个异步任务
	 * @param id
	 * @param uniqueIdentifier
	 * @param isHasError
	 * @param errorMessage
	 * @param result
	 */
	public void finish(Long id, String uniqueIdentifier, Boolean isHasError, String errorMessage, String result) {

		SchedulerAsyncDataTaskDO schedulerAsyncDataTaskDO = getDO(id, uniqueIdentifier, true);
		Long finishDictId = null;
		if (isHasError) {
			finishDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerDataTaskStatus.Group.scheduler_data_task_status.groupCode(), SchedulerDataTaskStatus.error.itemValue());
		}else {
			finishDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerDataTaskStatus.Group.scheduler_data_task_status.groupCode(), SchedulerDataTaskStatus.complete.itemValue());
		}

		schedulerAsyncDataTaskDO.setExecuteStatusDictId(finishDictId);
		schedulerAsyncDataTaskDO.setErrorMessage(errorMessage);
		schedulerAsyncDataTaskDO.setFinishAt(LocalDateTime.now());
		schedulerAsyncDataTaskDO.setResult(result);

		iSchedulerAsyncDataTaskService.updateById(schedulerAsyncDataTaskDO);
	}
	/**
	 * 获取
	 * @param id
	 * @param uniqueIdentifier
	 * @return
	 */
	public SchedulerAsyncDataTaskVO getVO(Long id, String uniqueIdentifier) {
		SchedulerAsyncDataTaskDO schedulerAsyncDataTaskDO = getDO(id, uniqueIdentifier, false);
		SchedulerAsyncDataTaskVO schedulerAsyncDataTaskVO = SchedulerAsyncDataTaskAppStructMapping.instance.schedulerAsyncDataTaskDOToSchedulerAsyncDataTaskVO(schedulerAsyncDataTaskDO);
		return schedulerAsyncDataTaskVO;
	}
	/**
	 * 获取
	 * @param id
	 * @param uniqueIdentifier
	 * @param useAssertWhenNotExist
	 * @return
	 */
	private SchedulerAsyncDataTaskDO getDO(Long id, String uniqueIdentifier,boolean useAssertWhenNotExist) {
		SchedulerAsyncDataTaskDO schedulerAsyncDataTaskDO = null;
		if (id != null) {
			schedulerAsyncDataTaskDO = iSchedulerAsyncDataTaskService.getById(SchedulerAsyncDataTaskId.of(id));
		}else if (uniqueIdentifier != null) {
			schedulerAsyncDataTaskDO = iSchedulerAsyncDataTaskService.getByUniqueIdentifier(uniqueIdentifier);
		}
		if (schedulerAsyncDataTaskDO == null && useAssertWhenNotExist) {
			Assert.isTrue(schedulerAsyncDataTaskDO != null, StrUtil.format("未找到该任务,id={},uniqueIdentifier={}",id,uniqueIdentifier));
		}
		return schedulerAsyncDataTaskDO;
	}

	/**
	 * 注入使用set方法
	 * @param schedulerAsyncDataTaskGateway
	 */
	@Autowired
	public void setSchedulerAsyncDataTaskGateway(SchedulerAsyncDataTaskGateway schedulerAsyncDataTaskGateway) {
		this.schedulerAsyncDataTaskGateway = schedulerAsyncDataTaskGateway;
	}
	@Autowired
	public void setISchedulerAsyncDataTaskService(ISchedulerAsyncDataTaskService iSchedulerAsyncDataTaskService) {
		this.iSchedulerAsyncDataTaskService = iSchedulerAsyncDataTaskService;
	}
	@Autowired
	public void setSchedulerDictGateway(SchedulerDictGateway schedulerDictGateway) {
		this.schedulerDictGateway = schedulerDictGateway;
	}
	@Autowired
	public void setSchedulerAsyncDataTaskCreateCommandExecutor(SchedulerAsyncDataTaskCreateCommandExecutor schedulerAsyncDataTaskCreateCommandExecutor) {
		this.schedulerAsyncDataTaskCreateCommandExecutor = schedulerAsyncDataTaskCreateCommandExecutor;
	}
}
