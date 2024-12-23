package com.particle.scheduler.infrastructure.temptask.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLog;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLogId;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskRunRecordLogGateway;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordLogDO;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordLogService;
import com.particle.scheduler.infrastructure.temptask.structmapping.SchedulerTempTaskRunRecordLogInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 任务计划临时任务运行记录日志 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Component
public class SchedulerTempTaskRunRecordLogGatewayImpl extends AbstractBaseGatewayImpl<SchedulerTempTaskRunRecordLogId,SchedulerTempTaskRunRecordLog> implements SchedulerTempTaskRunRecordLogGateway {

    private ISchedulerTempTaskRunRecordLogService iSchedulerTempTaskRunRecordLogService;

    @Override
    public SchedulerTempTaskRunRecordLog getById(SchedulerTempTaskRunRecordLogId schedulerTempTaskRunRecordLogId) {
        SchedulerTempTaskRunRecordLogDO byId = iSchedulerTempTaskRunRecordLogService.getById(schedulerTempTaskRunRecordLogId.getId());
        SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog = DomainFactory.create(SchedulerTempTaskRunRecordLog.class);
        schedulerTempTaskRunRecordLog = SchedulerTempTaskRunRecordLogInfrastructureStructMapping.instance. schedulerTempTaskRunRecordLogDOToSchedulerTempTaskRunRecordLog(schedulerTempTaskRunRecordLog,byId);
        return schedulerTempTaskRunRecordLog;
    }

    @Override
    public boolean doSave(SchedulerTempTaskRunRecordLog schedulerTempTaskRunRecordLog) {
        SchedulerTempTaskRunRecordLogDO schedulerTempTaskRunRecordLogDO = SchedulerTempTaskRunRecordLogInfrastructureStructMapping.instance.schedulerTempTaskRunRecordLogToSchedulerTempTaskRunRecordLogDO(schedulerTempTaskRunRecordLog);
        if (schedulerTempTaskRunRecordLogDO.getId() == null) {
            schedulerTempTaskRunRecordLogDO.setAddControl(schedulerTempTaskRunRecordLog.getAddControl());
            SchedulerTempTaskRunRecordLogDO add = iSchedulerTempTaskRunRecordLogService.add(schedulerTempTaskRunRecordLogDO);
            schedulerTempTaskRunRecordLog.setId(SchedulerTempTaskRunRecordLogId.of(add.getId()));
            return add != null;
        }
        schedulerTempTaskRunRecordLogDO.setUpdateControl(schedulerTempTaskRunRecordLog.getUpdateControl());
        SchedulerTempTaskRunRecordLogDO update = iSchedulerTempTaskRunRecordLogService.update(schedulerTempTaskRunRecordLogDO);
        return update != null;
    }

    @Override
    public boolean delete(SchedulerTempTaskRunRecordLogId schedulerTempTaskRunRecordLogId) {
        return iSchedulerTempTaskRunRecordLogService.deleteById(schedulerTempTaskRunRecordLogId.getId());
    }

    @Override
    public boolean delete(SchedulerTempTaskRunRecordLogId schedulerTempTaskRunRecordLogId, IdCommand idCommand) {
        return iSchedulerTempTaskRunRecordLogService.deleteById(idCommand);
    }

    @Autowired
    public void setISchedulerTempTaskRunRecordLogService(ISchedulerTempTaskRunRecordLogService iSchedulerTempTaskRunRecordLogService) {
        this.iSchedulerTempTaskRunRecordLogService = iSchedulerTempTaskRunRecordLogService;
    }
}
