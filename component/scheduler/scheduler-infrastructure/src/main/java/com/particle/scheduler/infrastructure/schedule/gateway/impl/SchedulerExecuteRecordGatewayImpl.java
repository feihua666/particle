package com.particle.scheduler.infrastructure.schedule.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecord;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecordId;
import com.particle.scheduler.domain.schedule.gateway.SchedulerExecuteRecordGateway;
import com.particle.scheduler.infrastructure.schedule.dos.SchedulerExecuteRecordDO;
import com.particle.scheduler.infrastructure.schedule.service.ISchedulerExecuteRecordService;
import com.particle.scheduler.infrastructure.schedule.structmapping.SchedulerExecuteRecordInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 任务计划执行记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Component
public class SchedulerExecuteRecordGatewayImpl extends AbstractBaseGatewayImpl<SchedulerExecuteRecordId,SchedulerExecuteRecord> implements SchedulerExecuteRecordGateway {

    private ISchedulerExecuteRecordService iSchedulerExecuteRecordService;

    @Override
    public SchedulerExecuteRecord getById(SchedulerExecuteRecordId schedulerExecuteRecordId) {
        SchedulerExecuteRecordDO byId = iSchedulerExecuteRecordService.getById(schedulerExecuteRecordId.getId());
        SchedulerExecuteRecord schedulerExecuteRecord = DomainFactory.create(SchedulerExecuteRecord.class);
        schedulerExecuteRecord = SchedulerExecuteRecordInfrastructureStructMapping.instance. schedulerExecuteRecordDOToSchedulerExecuteRecord(schedulerExecuteRecord,byId);
        return schedulerExecuteRecord;
    }

    @Override
    public boolean doSave(SchedulerExecuteRecord schedulerExecuteRecord) {
        SchedulerExecuteRecordDO schedulerExecuteRecordDO = SchedulerExecuteRecordInfrastructureStructMapping.instance.schedulerExecuteRecordToSchedulerExecuteRecordDO(schedulerExecuteRecord);
        if (schedulerExecuteRecordDO.getId() == null) {
            schedulerExecuteRecordDO.setAddControl(schedulerExecuteRecord.getAddControl());
            SchedulerExecuteRecordDO add = iSchedulerExecuteRecordService.add(schedulerExecuteRecordDO);
            schedulerExecuteRecord.setId(SchedulerExecuteRecordId.of(add.getId()));
            return add != null;
        }
        schedulerExecuteRecordDO.setUpdateControl(schedulerExecuteRecord.getUpdateControl());
        SchedulerExecuteRecordDO update = iSchedulerExecuteRecordService.update(schedulerExecuteRecordDO);
        return update != null;
    }

    @Override
    public boolean delete(SchedulerExecuteRecordId schedulerExecuteRecordId) {
        return iSchedulerExecuteRecordService.deleteById(schedulerExecuteRecordId.getId());
    }

    @Override
    public boolean delete(SchedulerExecuteRecordId schedulerExecuteRecordId, IdCommand idCommand) {
        return iSchedulerExecuteRecordService.deleteById(idCommand);
    }

    @Autowired
    public void setISchedulerExecuteRecordService(ISchedulerExecuteRecordService iSchedulerExecuteRecordService) {
        this.iSchedulerExecuteRecordService = iSchedulerExecuteRecordService;
    }
}
