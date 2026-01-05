package com.particle.scheduler.app.temptask.executor;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.NetUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.tool.log.TraceTool;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskCreateCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordCreateCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordLogCreateCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskFinishCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskLogCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskStartCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
import com.particle.scheduler.domain.enums.SchedulerTempTaskRunRecordStatus;
import com.particle.scheduler.domain.gateway.SchedulerDictGateway;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskGateway;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskDO;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordService;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskService;
import jakarta.validation.Valid;
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

    /**
     * 启动任务,表示启动一个任务计划临时任务
     * @param schedulerTempTaskStartCommand
     * @return 返回任务运行记录（运行实例）id
     */
	public SingleResponse<Long> start(@Valid SchedulerTempTaskStartCommand schedulerTempTaskStartCommand) {
        String code = schedulerTempTaskStartCommand.getCode();
        String name = schedulerTempTaskStartCommand.getName();
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
		return SingleResponse.of( execute.getData().getId());
	}

    /**
     * 结束任务
     * @param schedulerTempTaskFinishCommand
     */
	public Response finish(@Valid SchedulerTempTaskFinishCommand schedulerTempTaskFinishCommand) {
        Long id = schedulerTempTaskFinishCommand.getId();
        boolean isHasError = schedulerTempTaskFinishCommand.getIsHasError();
        String result = schedulerTempTaskFinishCommand.getResult();
		SchedulerTempTaskRunRecordDO schedulerTempTaskRunRecordDO = iSchedulerTempTaskRunRecordService.getById(id);
		if (schedulerTempTaskRunRecordDO != null) {
			Long finishStatusDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerTempTaskRunRecordStatus.running.groupCode(), SchedulerTempTaskRunRecordStatus.finished.itemValue());
			schedulerTempTaskRunRecordDO.setFinishAt(LocalDateTime.now());
			schedulerTempTaskRunRecordDO.setIsHasError(isHasError);
			schedulerTempTaskRunRecordDO.setExecuteStatusDictId(finishStatusDictId);
			schedulerTempTaskRunRecordDO.setResult(result);
			iSchedulerTempTaskRunRecordService.updateById(schedulerTempTaskRunRecordDO);
		}

        return Response.buildSuccess();
	}

    /**
     * 日志记录
     * @param schedulerTempTaskLogCommand
     * @return
     */
	public Response log(@Valid SchedulerTempTaskLogCommand schedulerTempTaskLogCommand) {
        Long id = schedulerTempTaskLogCommand.getId();
        String level = schedulerTempTaskLogCommand.getLevel();
        String message = schedulerTempTaskLogCommand.getMessage();

		SchedulerTempTaskRunRecordLogCreateCommand schedulerTempTaskRunRecordLogCreateCommand = new SchedulerTempTaskRunRecordLogCreateCommand();
		schedulerTempTaskRunRecordLogCreateCommand.setSchedulerTempTaskRunRecordId(id);
		schedulerTempTaskRunRecordLogCreateCommand.setLevel(level);
		schedulerTempTaskRunRecordLogCreateCommand.setContent(message);
		schedulerTempTaskRunRecordLogCreateCommandExecutor.execute(schedulerTempTaskRunRecordLogCreateCommand);
        return Response.buildSuccess();
	}

    /**
     * 检查是否允许运行切换
     * @param id
     * @return
     */
	public Response checkIsAllowRunSwitch(Long id) {
		SchedulerTempTaskRunRecordDO schedulerTempTaskRunRecordDO = iSchedulerTempTaskRunRecordService.getById(id);
		if (schedulerTempTaskRunRecordDO == null) {
			return Response.buildUnSuccess();
		}
        Boolean isAllowRunSwitch = schedulerTempTaskRunRecordDO.getIsAllowRunSwitch();
        return isAllowRunSwitch ? Response.buildSuccess() : Response.buildUnSuccess();
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
