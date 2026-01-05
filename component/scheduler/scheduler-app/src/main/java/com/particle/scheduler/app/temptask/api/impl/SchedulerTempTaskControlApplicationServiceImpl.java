package com.particle.scheduler.app.temptask.api.impl;

import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskCreateCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskDeleteCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskUpdateCommandExecutor;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskControlApplicationService;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskFinishCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskLogBaseCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskLogCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskStartCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 任务计划临时任务 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Transactional
@Service
@CatchAndLog
public class SchedulerTempTaskControlApplicationServiceImpl implements ISchedulerTempTaskControlApplicationService {

    private SchedulerTempTaskCreateCommandExecutor schedulerTempTaskCreateCommandExecutor;

    private SchedulerTempTaskDeleteCommandExecutor schedulerTempTaskDeleteCommandExecutor;

    private SchedulerTempTaskUpdateCommandExecutor schedulerTempTaskUpdateCommandExecutor;

    private SchedulerTempTaskCommandExecutor schedulerTempTaskCommandExecutor;

    @Override
    public SingleResponse<Long> start(SchedulerTempTaskStartCommand schedulerTempTaskStartCommand) {
        return schedulerTempTaskCommandExecutor.start(schedulerTempTaskStartCommand);
    }

    @Override
    public Response finish(SchedulerTempTaskFinishCommand schedulerTempTaskFinishCommand) {
        return schedulerTempTaskCommandExecutor.finish(schedulerTempTaskFinishCommand);
    }

    @Override
    public Response log(SchedulerTempTaskLogCommand schedulerTempTaskLogCommand) {
        return schedulerTempTaskCommandExecutor.log(schedulerTempTaskLogCommand);
    }

    @Override
    public Response logInfo(SchedulerTempTaskLogBaseCommand schedulerTempTaskLogBaseCommand) {
        return this.log(schedulerTempTaskLogBaseCommand.toLogCommand("info"));
    }

    @Override
    public Response logError(SchedulerTempTaskLogBaseCommand schedulerTempTaskLogBaseCommand) {
        return this.log(schedulerTempTaskLogBaseCommand.toLogCommand("error"));
    }

    @Override
    public Response checkIsAllowRunSwitch(Long id) {
        return schedulerTempTaskCommandExecutor.checkIsAllowRunSwitch(id);
    }

    @Autowired
    public void setSchedulerTempTaskCreateCommandExecutor(SchedulerTempTaskCreateCommandExecutor schedulerTempTaskCreateCommandExecutor) {
        this.schedulerTempTaskCreateCommandExecutor = schedulerTempTaskCreateCommandExecutor;
    }

    @Autowired
    public void setSchedulerTempTaskDeleteCommandExecutor(SchedulerTempTaskDeleteCommandExecutor schedulerTempTaskDeleteCommandExecutor) {
        this.schedulerTempTaskDeleteCommandExecutor = schedulerTempTaskDeleteCommandExecutor;
    }
    @Autowired
    public void setSchedulerTempTaskUpdateCommandExecutor(SchedulerTempTaskUpdateCommandExecutor schedulerTempTaskUpdateCommandExecutor) {
        this.schedulerTempTaskUpdateCommandExecutor = schedulerTempTaskUpdateCommandExecutor;
    }
    @Autowired
    public void setSchedulerTempTaskCommandExecutor(SchedulerTempTaskCommandExecutor schedulerTempTaskCommandExecutor) {
        this.schedulerTempTaskCommandExecutor = schedulerTempTaskCommandExecutor;
    }
}
