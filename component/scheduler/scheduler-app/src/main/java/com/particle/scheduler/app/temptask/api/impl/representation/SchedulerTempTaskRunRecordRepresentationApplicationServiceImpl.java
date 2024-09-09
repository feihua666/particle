package com.particle.scheduler.app.temptask.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.app.temptask.executor.representation.SchedulerTempTaskRunRecordQueryCommandExecutor;
import com.particle.scheduler.client.temptask.api.representation.ISchedulerTempTaskRunRecordRepresentationApplicationService;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordPageQueryCommand;
import com.particle.scheduler.client.temptask.dto.command.representation.SchedulerTempTaskRunRecordQueryListCommand;
import com.particle.scheduler.client.temptask.dto.data.SchedulerTempTaskRunRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 任务计划临时任务运行记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Service
@CatchAndLog
public class SchedulerTempTaskRunRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISchedulerTempTaskRunRecordRepresentationApplicationService {

    private SchedulerTempTaskRunRecordQueryCommandExecutor schedulerTempTaskRunRecordQueryCommandExecutor;

    @Override
    public SingleResponse<SchedulerTempTaskRunRecordVO> queryDetail(IdCommand detailCommand) {
        return schedulerTempTaskRunRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<SchedulerTempTaskRunRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return schedulerTempTaskRunRecordQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<SchedulerTempTaskRunRecordVO> pageQuery(SchedulerTempTaskRunRecordPageQueryCommand schedulerTempTaskRunRecordPageQueryCommand) {
        return schedulerTempTaskRunRecordQueryCommandExecutor.execute(schedulerTempTaskRunRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<SchedulerTempTaskRunRecordVO> queryList(SchedulerTempTaskRunRecordQueryListCommand schedulerTempTaskRunRecordQueryListCommand) {
        return schedulerTempTaskRunRecordQueryCommandExecutor.execute(schedulerTempTaskRunRecordQueryListCommand);
    }


    @Autowired
    public void setSchedulerTempTaskRunRecordQueryCommandExecutor(SchedulerTempTaskRunRecordQueryCommandExecutor schedulerTempTaskRunRecordQueryCommandExecutor) {
        this.schedulerTempTaskRunRecordQueryCommandExecutor = schedulerTempTaskRunRecordQueryCommandExecutor;
    }
}
