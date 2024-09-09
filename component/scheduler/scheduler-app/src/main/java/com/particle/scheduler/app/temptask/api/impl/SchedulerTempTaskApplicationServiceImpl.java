package com.particle.scheduler.app.temptask.api.impl;

import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskCreateCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskDeleteCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskUpdateCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskUpdateCommand;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskApplicationService;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskCreateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
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
public class SchedulerTempTaskApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerTempTaskApplicationService {

    private SchedulerTempTaskCreateCommandExecutor schedulerTempTaskCreateCommandExecutor;

    private SchedulerTempTaskDeleteCommandExecutor schedulerTempTaskDeleteCommandExecutor;

    private SchedulerTempTaskUpdateCommandExecutor schedulerTempTaskUpdateCommandExecutor;

    private SchedulerTempTaskCommandExecutor schedulerTempTaskCommandExecutor;


    @Override
    public SingleResponse<SchedulerTempTaskVO> create(SchedulerTempTaskCreateCommand schedulerTempTaskCreateCommand) {
        return schedulerTempTaskCreateCommandExecutor.execute(schedulerTempTaskCreateCommand);
    }

    @Override
    public SingleResponse<SchedulerTempTaskVO> delete(IdCommand deleteCommand) {
        return schedulerTempTaskDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<SchedulerTempTaskVO> update(SchedulerTempTaskUpdateCommand schedulerTempTaskUpdateCommand) {
        return schedulerTempTaskUpdateCommandExecutor.execute(schedulerTempTaskUpdateCommand);
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
