package com.particle.scheduler.app.temptask.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskRunRecordLogCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskRunRecordLogCreateCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskRunRecordLogDeleteCommandExecutor;
import com.particle.scheduler.app.temptask.executor.SchedulerTempTaskRunRecordLogUpdateCommandExecutor;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskRunRecordLogApplicationService;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordLogCreateCommand;
import com.particle.scheduler.client.temptask.dto.command.SchedulerTempTaskRunRecordLogUpdateCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 任务计划临时任务运行记录日志 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Transactional
@Service
@CatchAndLog
public class SchedulerTempTaskRunRecordLogApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerTempTaskRunRecordLogApplicationService {

    private SchedulerTempTaskRunRecordLogCreateCommandExecutor schedulerTempTaskRunRecordLogCreateCommandExecutor;

    private SchedulerTempTaskRunRecordLogDeleteCommandExecutor schedulerTempTaskRunRecordLogDeleteCommandExecutor;

    private SchedulerTempTaskRunRecordLogUpdateCommandExecutor schedulerTempTaskRunRecordLogUpdateCommandExecutor;

    private SchedulerTempTaskRunRecordLogCommandExecutor schedulerTempTaskRunRecordLogCommandExecutor;


    @Override
    public SingleResponse<SchedulerTempTaskRunRecordLogVO> create(SchedulerTempTaskRunRecordLogCreateCommand schedulerTempTaskRunRecordLogCreateCommand) {
        return schedulerTempTaskRunRecordLogCreateCommandExecutor.execute(schedulerTempTaskRunRecordLogCreateCommand);
    }

    @Override
    public SingleResponse<SchedulerTempTaskRunRecordLogVO> delete(IdCommand deleteCommand) {
        return schedulerTempTaskRunRecordLogDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<SchedulerTempTaskRunRecordLogVO> update(SchedulerTempTaskRunRecordLogUpdateCommand schedulerTempTaskRunRecordLogUpdateCommand) {
        return schedulerTempTaskRunRecordLogUpdateCommandExecutor.execute(schedulerTempTaskRunRecordLogUpdateCommand);
    }


    @Autowired
    public void setSchedulerTempTaskRunRecordLogCreateCommandExecutor(SchedulerTempTaskRunRecordLogCreateCommandExecutor schedulerTempTaskRunRecordLogCreateCommandExecutor) {
        this.schedulerTempTaskRunRecordLogCreateCommandExecutor = schedulerTempTaskRunRecordLogCreateCommandExecutor;
    }

    @Autowired
    public void setSchedulerTempTaskRunRecordLogDeleteCommandExecutor(SchedulerTempTaskRunRecordLogDeleteCommandExecutor schedulerTempTaskRunRecordLogDeleteCommandExecutor) {
        this.schedulerTempTaskRunRecordLogDeleteCommandExecutor = schedulerTempTaskRunRecordLogDeleteCommandExecutor;
    }
    @Autowired
    public void setSchedulerTempTaskRunRecordLogUpdateCommandExecutor(SchedulerTempTaskRunRecordLogUpdateCommandExecutor schedulerTempTaskRunRecordLogUpdateCommandExecutor) {
        this.schedulerTempTaskRunRecordLogUpdateCommandExecutor = schedulerTempTaskRunRecordLogUpdateCommandExecutor;
    }
    @Autowired
    public void setSchedulerTempTaskRunRecordLogCommandExecutor(SchedulerTempTaskRunRecordLogCommandExecutor schedulerTempTaskRunRecordLogCommandExecutor) {
        this.schedulerTempTaskRunRecordLogCommandExecutor = schedulerTempTaskRunRecordLogCommandExecutor;
    }
}
