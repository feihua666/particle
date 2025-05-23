package com.particle.scheduler.app.datatask.api.impl;

import com.particle.scheduler.app.datatask.executor.SchedulerJobDataTaskCreateCommandExecutor;
import com.particle.scheduler.app.datatask.executor.SchedulerJobDataTaskDeleteCommandExecutor;
import com.particle.scheduler.app.datatask.executor.SchedulerJobDataTaskUpdateCommandExecutor;
import com.particle.scheduler.app.datatask.executor.SchedulerJobDataTaskCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.scheduler.client.datatask.dto.command.SchedulerJobDataTaskUpdateCommand;
import com.particle.scheduler.client.datatask.api.ISchedulerJobDataTaskApplicationService;
import com.particle.scheduler.client.datatask.dto.command.SchedulerJobDataTaskCreateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 任务计划任务数据 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Transactional
@Service
@CatchAndLog
public class SchedulerJobDataTaskApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerJobDataTaskApplicationService {

    private SchedulerJobDataTaskCreateCommandExecutor schedulerJobDataTaskCreateCommandExecutor;

    private SchedulerJobDataTaskDeleteCommandExecutor schedulerJobDataTaskDeleteCommandExecutor;

    private SchedulerJobDataTaskUpdateCommandExecutor schedulerJobDataTaskUpdateCommandExecutor;

    private SchedulerJobDataTaskCommandExecutor schedulerJobDataTaskCommandExecutor;


    @Override
    public SingleResponse<SchedulerJobDataTaskVO> create(SchedulerJobDataTaskCreateCommand schedulerJobDataTaskCreateCommand) {
        return schedulerJobDataTaskCreateCommandExecutor.execute(schedulerJobDataTaskCreateCommand);
    }

    @Override
    public SingleResponse<SchedulerJobDataTaskVO> delete(IdCommand deleteCommand) {
        return schedulerJobDataTaskDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<SchedulerJobDataTaskVO> update(SchedulerJobDataTaskUpdateCommand schedulerJobDataTaskUpdateCommand) {
        return schedulerJobDataTaskUpdateCommandExecutor.execute(schedulerJobDataTaskUpdateCommand);
    }


    @Autowired
    public void setSchedulerJobDataTaskCreateCommandExecutor(SchedulerJobDataTaskCreateCommandExecutor schedulerJobDataTaskCreateCommandExecutor) {
        this.schedulerJobDataTaskCreateCommandExecutor = schedulerJobDataTaskCreateCommandExecutor;
    }

    @Autowired
    public void setSchedulerJobDataTaskDeleteCommandExecutor(SchedulerJobDataTaskDeleteCommandExecutor schedulerJobDataTaskDeleteCommandExecutor) {
        this.schedulerJobDataTaskDeleteCommandExecutor = schedulerJobDataTaskDeleteCommandExecutor;
    }
    @Autowired
    public void setSchedulerJobDataTaskUpdateCommandExecutor(SchedulerJobDataTaskUpdateCommandExecutor schedulerJobDataTaskUpdateCommandExecutor) {
        this.schedulerJobDataTaskUpdateCommandExecutor = schedulerJobDataTaskUpdateCommandExecutor;
    }
    @Autowired
    public void setSchedulerJobDataTaskCommandExecutor(SchedulerJobDataTaskCommandExecutor schedulerJobDataTaskCommandExecutor) {
        this.schedulerJobDataTaskCommandExecutor = schedulerJobDataTaskCommandExecutor;
    }
}
