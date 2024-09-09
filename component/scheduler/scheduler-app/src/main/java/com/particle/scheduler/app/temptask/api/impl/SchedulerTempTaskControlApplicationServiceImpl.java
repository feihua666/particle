package com.particle.scheduler.app.temptask.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskCreateCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskDeleteCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskUpdateCommandExecutor;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskApplicationService;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskControlApplicationService;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskCreateCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskUpdateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;
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
    public Long start(String code, String name) {
        return schedulerTempTaskCommandExecutor.start(code,name);
    }

    @Override
    public void finish(Long id, Boolean isHasError,String result) {
        schedulerTempTaskCommandExecutor.finish(id, isHasError, result);
    }

    @Override
    public void log(String level, Long id, String message) {
        schedulerTempTaskCommandExecutor.log(level,id,message);
    }

    @Override
    public void logInfo(Long id, String message) {
        this.log("info",id,message);
    }

    @Override
    public void logError(Long id, String message) {
        this.log("error",id,message);
    }

    @Override
    public boolean checkIsAllowRunSwitch(Long id) {
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
