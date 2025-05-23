package com.particle.scheduler.app.datatask.executor;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.tool.log.TraceTool;
import com.particle.scheduler.app.datatask.structmapping.SchedulerJobDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.dto.command.SchedulerJobDataTaskCreateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTaskId;
import com.particle.scheduler.domain.datatask.gateway.SchedulerJobDataTaskGateway;
import com.particle.scheduler.domain.enums.SchedulerDataTaskStatus;
import com.particle.scheduler.domain.gateway.SchedulerDictGateway;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerJobDataTaskDO;
import com.particle.scheduler.infrastructure.datatask.service.ISchedulerJobDataTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划任务数据 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Component
@Validated
public class SchedulerJobDataTaskCommandExecutor  extends AbstractBaseExecutor {

	private SchedulerJobDataTaskGateway schedulerJobDataTaskGateway;
	private ISchedulerJobDataTaskService iSchedulerJobDataTaskService;
	private SchedulerDictGateway schedulerDictGateway;
	private SchedulerJobDataTaskCreateCommandExecutor schedulerJobDataTaskCreateCommandExecutor;


	/**
	 * 提交一个异步任务
	 * @param groupIdentifier
	 * @param uniqueIdentifier
	 * @param params
	 * @param dataExpireAt
	 * @return
	 */
	public SchedulerJobDataTaskVO submit(String groupIdentifier, String uniqueIdentifier,String params,LocalDateTime dataExpireAt) {
		SchedulerJobDataTaskDO schedulerJobDataTaskDO = getDO(null, uniqueIdentifier, true);

		if (schedulerJobDataTaskDO != null) {
			Assert.isTrue(schedulerJobDataTaskDO == null,StrUtil.format("已存在该任务，请勿重复提交，uniqueIdentifier={}", uniqueIdentifier));
		}

		SchedulerJobDataTaskCreateCommand schedulerJobDataTaskCreateCommand = new SchedulerJobDataTaskCreateCommand();
		schedulerJobDataTaskCreateCommand.setGroupIdentifier(groupIdentifier);
		schedulerJobDataTaskCreateCommand.setUniqueIdentifier(uniqueIdentifier);
		schedulerJobDataTaskCreateCommand.setParams(params);
		Long submitDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerDataTaskStatus.Group.scheduler_data_task_status.groupCode(), SchedulerDataTaskStatus.submited.itemValue());
		schedulerJobDataTaskCreateCommand.setExecuteStatusDictId(submitDictId);
		schedulerJobDataTaskCreateCommand.setLocalHostIp(NetUtil.getLocalhostStr());
		schedulerJobDataTaskCreateCommand.setLocalHostName(NetUtil.getLocalHostName());
		schedulerJobDataTaskCreateCommand.setTraceId(TraceTool.getTraceId());
		schedulerJobDataTaskCreateCommand.setDataExpireAt(dataExpireAt);

		SingleResponse<SchedulerJobDataTaskVO> execute = schedulerJobDataTaskCreateCommandExecutor.execute(schedulerJobDataTaskCreateCommand);
		return execute.getData();
	}

	/**
	 * 处理开始一个异步任务
	 * @param id
	 * @param uniqueIdentifier
	 */
	public void processStart(Long id, String uniqueIdentifier) {
		SchedulerJobDataTaskDO schedulerJobDataTaskDO = getDO(id, uniqueIdentifier, true);
		Long processDictId = null;
		processDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerDataTaskStatus.Group.scheduler_data_task_status.groupCode(), SchedulerDataTaskStatus.processing.itemValue());
		schedulerJobDataTaskDO.setExecuteStatusDictId(processDictId);
		iSchedulerJobDataTaskService.updateById(schedulerJobDataTaskDO);
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

		SchedulerJobDataTaskDO schedulerJobDataTaskDO = getDO(id, uniqueIdentifier, true);
		Long finishDictId = null;
		if (isHasError) {
			finishDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerDataTaskStatus.Group.scheduler_data_task_status.groupCode(), SchedulerDataTaskStatus.error.itemValue());
		}else {
			finishDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerDataTaskStatus.Group.scheduler_data_task_status.groupCode(), SchedulerDataTaskStatus.complete.itemValue());
		}

		schedulerJobDataTaskDO.setExecuteStatusDictId(finishDictId);
		schedulerJobDataTaskDO.setErrorMessage(errorMessage);
		schedulerJobDataTaskDO.setFinishAt(LocalDateTime.now());
		schedulerJobDataTaskDO.setResult(result);

		iSchedulerJobDataTaskService.updateById(schedulerJobDataTaskDO);
	}
	/**
	 * 获取
	 * @param id
	 * @param uniqueIdentifier
	 * @return
	 */
	public SchedulerJobDataTaskVO getVO(Long id, String uniqueIdentifier) {
		SchedulerJobDataTaskDO schedulerJobDataTaskDO = getDO(id, uniqueIdentifier, false);
		SchedulerJobDataTaskVO schedulerJobDataTaskVO = SchedulerJobDataTaskAppStructMapping.instance.schedulerJobDataTaskDOToSchedulerJobDataTaskVO(schedulerJobDataTaskDO);
		return schedulerJobDataTaskVO;
	}
	/**
	 * 获取
	 * @param id
	 * @param uniqueIdentifier
	 * @param useAssertWhenNotExist
	 * @return
	 */
	private SchedulerJobDataTaskDO getDO(Long id, String uniqueIdentifier,boolean useAssertWhenNotExist) {
		SchedulerJobDataTaskDO schedulerJobDataTaskDO = null;
		if (id != null) {
			schedulerJobDataTaskDO = iSchedulerJobDataTaskService.getById(SchedulerJobDataTaskId.of(id));
		}else if (uniqueIdentifier != null) {
			schedulerJobDataTaskDO = iSchedulerJobDataTaskService.getByUniqueIdentifier(uniqueIdentifier);
		}
		if (schedulerJobDataTaskDO == null && useAssertWhenNotExist) {
			Assert.isTrue(schedulerJobDataTaskDO != null, StrUtil.format("未找到该任务,id={},uniqueIdentifier={}",id,uniqueIdentifier));
		}
		return schedulerJobDataTaskDO;
	}

	/**
	 * 注入使用set方法
	 * @param schedulerJobDataTaskGateway
	 */
	@Autowired
	public void setSchedulerJobDataTaskGateway(SchedulerJobDataTaskGateway schedulerJobDataTaskGateway) {
		this.schedulerJobDataTaskGateway = schedulerJobDataTaskGateway;
	}
	@Autowired
	public void setISchedulerJobDataTaskService(ISchedulerJobDataTaskService iSchedulerJobDataTaskService) {
		this.iSchedulerJobDataTaskService = iSchedulerJobDataTaskService;
	}
	@Autowired
	public void setSchedulerDictGateway(SchedulerDictGateway schedulerDictGateway) {
		this.schedulerDictGateway = schedulerDictGateway;
	}
	@Autowired
	public void setSchedulerJobDataTaskCreateCommandExecutor(SchedulerJobDataTaskCreateCommandExecutor schedulerJobDataTaskCreateCommandExecutor) {
		this.schedulerJobDataTaskCreateCommandExecutor = schedulerJobDataTaskCreateCommandExecutor;
	}
}
