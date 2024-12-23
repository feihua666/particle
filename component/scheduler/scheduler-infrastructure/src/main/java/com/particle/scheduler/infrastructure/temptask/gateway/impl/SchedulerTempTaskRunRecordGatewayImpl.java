package com.particle.scheduler.infrastructure.temptask.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecord;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordId;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskRunRecordGateway;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordService;
import com.particle.scheduler.infrastructure.temptask.structmapping.SchedulerTempTaskRunRecordInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 任务计划临时任务运行记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Component
public class SchedulerTempTaskRunRecordGatewayImpl extends AbstractBaseGatewayImpl<SchedulerTempTaskRunRecordId,SchedulerTempTaskRunRecord> implements SchedulerTempTaskRunRecordGateway {

    private ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService;

    @Override
    public SchedulerTempTaskRunRecord getById(SchedulerTempTaskRunRecordId schedulerTempTaskRunRecordId) {
        SchedulerTempTaskRunRecordDO byId = iSchedulerTempTaskRunRecordService.getById(schedulerTempTaskRunRecordId.getId());
        SchedulerTempTaskRunRecord schedulerTempTaskRunRecord = DomainFactory.create(SchedulerTempTaskRunRecord.class);
        schedulerTempTaskRunRecord = SchedulerTempTaskRunRecordInfrastructureStructMapping.instance. schedulerTempTaskRunRecordDOToSchedulerTempTaskRunRecord(schedulerTempTaskRunRecord,byId);
        return schedulerTempTaskRunRecord;
    }

    @Override
    public boolean doSave(SchedulerTempTaskRunRecord schedulerTempTaskRunRecord) {
        SchedulerTempTaskRunRecordDO schedulerTempTaskRunRecordDO = SchedulerTempTaskRunRecordInfrastructureStructMapping.instance.schedulerTempTaskRunRecordToSchedulerTempTaskRunRecordDO(schedulerTempTaskRunRecord);
        if (schedulerTempTaskRunRecordDO.getId() == null) {
            schedulerTempTaskRunRecordDO.setAddControl(schedulerTempTaskRunRecord.getAddControl());
            SchedulerTempTaskRunRecordDO add = iSchedulerTempTaskRunRecordService.add(schedulerTempTaskRunRecordDO);
            schedulerTempTaskRunRecord.setId(SchedulerTempTaskRunRecordId.of(add.getId()));
            return add != null;
        }
        schedulerTempTaskRunRecordDO.setUpdateControl(schedulerTempTaskRunRecord.getUpdateControl());
        SchedulerTempTaskRunRecordDO update = iSchedulerTempTaskRunRecordService.update(schedulerTempTaskRunRecordDO);
        return update != null;
    }

    @Override
    public boolean delete(SchedulerTempTaskRunRecordId schedulerTempTaskRunRecordId) {
        return iSchedulerTempTaskRunRecordService.deleteById(schedulerTempTaskRunRecordId.getId());
    }

    @Override
    public boolean delete(SchedulerTempTaskRunRecordId schedulerTempTaskRunRecordId, IdCommand idCommand) {
        return iSchedulerTempTaskRunRecordService.deleteById(idCommand);
    }

    @Autowired
    public void setISchedulerTempTaskRunRecordService(ISchedulerTempTaskRunRecordService iSchedulerTempTaskRunRecordService) {
        this.iSchedulerTempTaskRunRecordService = iSchedulerTempTaskRunRecordService;
    }
}
