package com.particle.scheduler.app.temptask.api.impl;

import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskRunRecordCreateCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskRunRecordDeleteCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskRunRecordUpdateCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskRunRecordCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordUpdateCommand;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskRunRecordApplicationService;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordCreateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 任务计划临时任务运行记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Transactional
@Service
@CatchAndLog
public class SchedulerTempTaskRunRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerTempTaskRunRecordApplicationService {

    private SchedulerTempTaskRunRecordCreateCommandExecutor schedulerTempTaskRunRecordCreateCommandExecutor;

    private SchedulerTempTaskRunRecordDeleteCommandExecutor schedulerTempTaskRunRecordDeleteCommandExecutor;

    private SchedulerTempTaskRunRecordUpdateCommandExecutor schedulerTempTaskRunRecordUpdateCommandExecutor;

    private SchedulerTempTaskRunRecordCommandExecutor schedulerTempTaskRunRecordCommandExecutor;


    @Override
    public SingleResponse<SchedulerTempTaskRunRecordVO> create(SchedulerTempTaskRunRecordCreateCommand schedulerTempTaskRunRecordCreateCommand) {
        return schedulerTempTaskRunRecordCreateCommandExecutor.execute(schedulerTempTaskRunRecordCreateCommand);
    }

    @Override
    public SingleResponse<SchedulerTempTaskRunRecordVO> delete(IdCommand deleteCommand) {
        return schedulerTempTaskRunRecordDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<SchedulerTempTaskRunRecordVO> update(SchedulerTempTaskRunRecordUpdateCommand schedulerTempTaskRunRecordUpdateCommand) {
        return schedulerTempTaskRunRecordUpdateCommandExecutor.execute(schedulerTempTaskRunRecordUpdateCommand);
    }


    @Autowired
    public void setSchedulerTempTaskRunRecordCreateCommandExecutor(SchedulerTempTaskRunRecordCreateCommandExecutor schedulerTempTaskRunRecordCreateCommandExecutor) {
        this.schedulerTempTaskRunRecordCreateCommandExecutor = schedulerTempTaskRunRecordCreateCommandExecutor;
    }

    @Autowired
    public void setSchedulerTempTaskRunRecordDeleteCommandExecutor(SchedulerTempTaskRunRecordDeleteCommandExecutor schedulerTempTaskRunRecordDeleteCommandExecutor) {
        this.schedulerTempTaskRunRecordDeleteCommandExecutor = schedulerTempTaskRunRecordDeleteCommandExecutor;
    }
    @Autowired
    public void setSchedulerTempTaskRunRecordUpdateCommandExecutor(SchedulerTempTaskRunRecordUpdateCommandExecutor schedulerTempTaskRunRecordUpdateCommandExecutor) {
        this.schedulerTempTaskRunRecordUpdateCommandExecutor = schedulerTempTaskRunRecordUpdateCommandExecutor;
    }
    @Autowired
    public void setSchedulerTempTaskRunRecordCommandExecutor(SchedulerTempTaskRunRecordCommandExecutor schedulerTempTaskRunRecordCommandExecutor) {
        this.schedulerTempTaskRunRecordCommandExecutor = schedulerTempTaskRunRecordCommandExecutor;
    }
}
