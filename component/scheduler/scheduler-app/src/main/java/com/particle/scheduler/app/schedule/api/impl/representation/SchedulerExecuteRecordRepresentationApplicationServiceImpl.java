package com.particle.scheduler.app.schedule.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.schedule.executor.representation.SchedulerExecuteRecordQueryCommandExecutor;
import com.particle.scheduler.client.schedule.api.representation.ISchedulerExecuteRecordRepresentationApplicationService;
import com.particle.scheduler.client.schedule.dto.command.representation.SchedulerExecuteRecordPageQueryCommand;
import com.particle.scheduler.client.schedule.dto.command.representation.SchedulerExecuteRecordQueryListCommand;
import com.particle.scheduler.client.schedule.dto.data.SchedulerExecuteRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 任务计划执行记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Service
@CatchAndLog
public class SchedulerExecuteRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerExecuteRecordRepresentationApplicationService {

    private SchedulerExecuteRecordQueryCommandExecutor schedulerExecuteRecordQueryCommandExecutor;

    @Override
    public SingleResponse<SchedulerExecuteRecordVO> queryDetail(IdCommand detailCommand) {
        return schedulerExecuteRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<SchedulerExecuteRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return schedulerExecuteRecordQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<SchedulerExecuteRecordVO> pageQuery(SchedulerExecuteRecordPageQueryCommand schedulerExecuteRecordPageQueryCommand) {
        return schedulerExecuteRecordQueryCommandExecutor.execute(schedulerExecuteRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<SchedulerExecuteRecordVO> queryList(SchedulerExecuteRecordQueryListCommand schedulerExecuteRecordQueryListCommand) {
        return schedulerExecuteRecordQueryCommandExecutor.execute(schedulerExecuteRecordQueryListCommand);
    }


    @Autowired
    public void setSchedulerExecuteRecordQueryCommandExecutor(SchedulerExecuteRecordQueryCommandExecutor schedulerExecuteRecordQueryCommandExecutor) {
        this.schedulerExecuteRecordQueryCommandExecutor = schedulerExecuteRecordQueryCommandExecutor;
    }
}
