package com.particle.scheduler.app.schedule.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.schedule.executor.SchedulerExecuteRecordCommandExecutor;
import com.particle.scheduler.app.schedule.executor.SchedulerExecuteRecordCreateCommandExecutor;
import com.particle.scheduler.app.schedule.executor.SchedulerExecuteRecordDeleteCommandExecutor;
import com.particle.scheduler.app.schedule.executor.SchedulerExecuteRecordUpdateCommandExecutor;
import com.particle.scheduler.client.schedule.api.ISchedulerExecuteRecordApplicationService;
import com.particle.scheduler.client.schedule.dto.command.SchedulerExecuteRecordCreateCommand;
import com.particle.scheduler.client.schedule.dto.command.SchedulerExecuteRecordUpdateCommand;
import com.particle.scheduler.client.schedule.dto.data.SchedulerExecuteRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 任务计划执行记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Transactional
@Service
@CatchAndLog
public class SchedulerExecuteRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerExecuteRecordApplicationService {

    private SchedulerExecuteRecordCreateCommandExecutor schedulerExecuteRecordCreateCommandExecutor;

    private SchedulerExecuteRecordDeleteCommandExecutor schedulerExecuteRecordDeleteCommandExecutor;

    private SchedulerExecuteRecordUpdateCommandExecutor schedulerExecuteRecordUpdateCommandExecutor;

    private SchedulerExecuteRecordCommandExecutor schedulerExecuteRecordCommandExecutor;


    @Override
    public SingleResponse<SchedulerExecuteRecordVO> create(SchedulerExecuteRecordCreateCommand schedulerExecuteRecordCreateCommand) {
        return schedulerExecuteRecordCreateCommandExecutor.execute(schedulerExecuteRecordCreateCommand);
    }

    @Override
    public SingleResponse<SchedulerExecuteRecordVO> delete(IdCommand deleteCommand) {
        return schedulerExecuteRecordDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<SchedulerExecuteRecordVO> update(SchedulerExecuteRecordUpdateCommand schedulerExecuteRecordUpdateCommand) {
        return schedulerExecuteRecordUpdateCommandExecutor.execute(schedulerExecuteRecordUpdateCommand);
    }


    @Autowired
    public void setSchedulerExecuteRecordCreateCommandExecutor(SchedulerExecuteRecordCreateCommandExecutor schedulerExecuteRecordCreateCommandExecutor) {
        this.schedulerExecuteRecordCreateCommandExecutor = schedulerExecuteRecordCreateCommandExecutor;
    }

    @Autowired
    public void setSchedulerExecuteRecordDeleteCommandExecutor(SchedulerExecuteRecordDeleteCommandExecutor schedulerExecuteRecordDeleteCommandExecutor) {
        this.schedulerExecuteRecordDeleteCommandExecutor = schedulerExecuteRecordDeleteCommandExecutor;
    }
    @Autowired
    public void setSchedulerExecuteRecordUpdateCommandExecutor(SchedulerExecuteRecordUpdateCommandExecutor schedulerExecuteRecordUpdateCommandExecutor) {
        this.schedulerExecuteRecordUpdateCommandExecutor = schedulerExecuteRecordUpdateCommandExecutor;
    }
    @Autowired
    public void setSchedulerExecuteRecordCommandExecutor(SchedulerExecuteRecordCommandExecutor schedulerExecuteRecordCommandExecutor) {
        this.schedulerExecuteRecordCommandExecutor = schedulerExecuteRecordCommandExecutor;
    }
}
