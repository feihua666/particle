package com.particle.scheduler.app.temptask.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.temptask.executor.representation.SchedulerTempTaskRunRecordLogQueryCommandExecutor;
import com.particle.scheduler.client.temptask.api.representation.ISchedulerTempTaskRunRecordLogRepresentationApplicationService;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordLogPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordLogQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 任务计划临时任务运行记录日志 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Service
@CatchAndLog
public class SchedulerTempTaskRunRecordLogRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerTempTaskRunRecordLogRepresentationApplicationService {

    private SchedulerTempTaskRunRecordLogQueryCommandExecutor schedulerTempTaskRunRecordLogQueryCommandExecutor;

    @Override
    public SingleResponse<SchedulerTempTaskRunRecordLogVO> queryDetail(IdCommand detailCommand) {
        return schedulerTempTaskRunRecordLogQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<SchedulerTempTaskRunRecordLogVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return schedulerTempTaskRunRecordLogQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<SchedulerTempTaskRunRecordLogVO> pageQuery(SchedulerTempTaskRunRecordLogPageQueryCommand schedulerTempTaskRunRecordLogPageQueryCommand) {
        return schedulerTempTaskRunRecordLogQueryCommandExecutor.execute(schedulerTempTaskRunRecordLogPageQueryCommand);
    }

    @Override
    public MultiResponse<SchedulerTempTaskRunRecordLogVO> queryList(SchedulerTempTaskRunRecordLogQueryListCommand schedulerTempTaskRunRecordLogQueryListCommand) {
        return schedulerTempTaskRunRecordLogQueryCommandExecutor.execute(schedulerTempTaskRunRecordLogQueryListCommand);
    }


    @Autowired
    public void setSchedulerTempTaskRunRecordLogQueryCommandExecutor(SchedulerTempTaskRunRecordLogQueryCommandExecutor schedulerTempTaskRunRecordLogQueryCommandExecutor) {
        this.schedulerTempTaskRunRecordLogQueryCommandExecutor = schedulerTempTaskRunRecordLogQueryCommandExecutor;
    }
}
