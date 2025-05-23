package com.particle.scheduler.app.datatask.api.impl;

import com.particle.scheduler.app.datatask.executor.SchedulerAsyncDataTaskCreateCommandExecutor;
import com.particle.scheduler.app.datatask.executor.SchedulerAsyncDataTaskDeleteCommandExecutor;
import com.particle.scheduler.app.datatask.executor.SchedulerAsyncDataTaskUpdateCommandExecutor;
import com.particle.scheduler.app.datatask.executor.SchedulerAsyncDataTaskCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.scheduler.client.datatask.dto.command.SchedulerAsyncDataTaskUpdateCommand;
import com.particle.scheduler.client.datatask.api.ISchedulerAsyncDataTaskApplicationService;
import com.particle.scheduler.client.datatask.dto.command.SchedulerAsyncDataTaskCreateCommand;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 任务计划异步任务数据 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Transactional
@Service
@CatchAndLog
public class SchedulerAsyncDataTaskApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerAsyncDataTaskApplicationService {

    private SchedulerAsyncDataTaskCreateCommandExecutor schedulerAsyncDataTaskCreateCommandExecutor;

    private SchedulerAsyncDataTaskDeleteCommandExecutor schedulerAsyncDataTaskDeleteCommandExecutor;

    private SchedulerAsyncDataTaskUpdateCommandExecutor schedulerAsyncDataTaskUpdateCommandExecutor;

    private SchedulerAsyncDataTaskCommandExecutor schedulerAsyncDataTaskCommandExecutor;


    @Override
    public SingleResponse<SchedulerAsyncDataTaskVO> create(SchedulerAsyncDataTaskCreateCommand schedulerAsyncDataTaskCreateCommand) {
        return schedulerAsyncDataTaskCreateCommandExecutor.execute(schedulerAsyncDataTaskCreateCommand);
    }

    @Override
    public SingleResponse<SchedulerAsyncDataTaskVO> delete(IdCommand deleteCommand) {
        return schedulerAsyncDataTaskDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<SchedulerAsyncDataTaskVO> update(SchedulerAsyncDataTaskUpdateCommand schedulerAsyncDataTaskUpdateCommand) {
        return schedulerAsyncDataTaskUpdateCommandExecutor.execute(schedulerAsyncDataTaskUpdateCommand);
    }


    @Autowired
    public void setSchedulerAsyncDataTaskCreateCommandExecutor(SchedulerAsyncDataTaskCreateCommandExecutor schedulerAsyncDataTaskCreateCommandExecutor) {
        this.schedulerAsyncDataTaskCreateCommandExecutor = schedulerAsyncDataTaskCreateCommandExecutor;
    }

    @Autowired
    public void setSchedulerAsyncDataTaskDeleteCommandExecutor(SchedulerAsyncDataTaskDeleteCommandExecutor schedulerAsyncDataTaskDeleteCommandExecutor) {
        this.schedulerAsyncDataTaskDeleteCommandExecutor = schedulerAsyncDataTaskDeleteCommandExecutor;
    }
    @Autowired
    public void setSchedulerAsyncDataTaskUpdateCommandExecutor(SchedulerAsyncDataTaskUpdateCommandExecutor schedulerAsyncDataTaskUpdateCommandExecutor) {
        this.schedulerAsyncDataTaskUpdateCommandExecutor = schedulerAsyncDataTaskUpdateCommandExecutor;
    }
    @Autowired
    public void setSchedulerAsyncDataTaskCommandExecutor(SchedulerAsyncDataTaskCommandExecutor schedulerAsyncDataTaskCommandExecutor) {
        this.schedulerAsyncDataTaskCommandExecutor = schedulerAsyncDataTaskCommandExecutor;
    }
}
