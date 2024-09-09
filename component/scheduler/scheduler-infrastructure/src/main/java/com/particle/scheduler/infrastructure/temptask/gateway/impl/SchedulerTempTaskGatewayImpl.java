package com.particle.scheduler.infrastructure.temptask.gateway.impl;

import com.particle.scheduler.domain.temptask.SchedulerTempTask;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskId;
import com.particle.scheduler.domain.temptask.gateway.SchedulerTempTaskGateway;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskService;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskDO;
import com.particle.scheduler.infrastructure.temptask.structmapping.SchedulerTempTaskInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 任务计划临时任务 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Component
public class SchedulerTempTaskGatewayImpl extends AbstractBaseGatewayImpl<SchedulerTempTaskId,SchedulerTempTask> implements SchedulerTempTaskGateway {

    private ISchedulerTempTaskService iSchedulerTempTaskService;

    @Override
    public SchedulerTempTask getById(SchedulerTempTaskId schedulerTempTaskId) {
        SchedulerTempTaskDO byId = iSchedulerTempTaskService.getById(schedulerTempTaskId.getId());
        SchedulerTempTask schedulerTempTask = DomainFactory.create(SchedulerTempTask.class);
        schedulerTempTask = SchedulerTempTaskInfrastructureStructMapping.instance. schedulerTempTaskDOToSchedulerTempTask(schedulerTempTask,byId);
        return schedulerTempTask;
    }

    @Override
    public boolean doSave(SchedulerTempTask schedulerTempTask) {
        SchedulerTempTaskDO schedulerTempTaskDO = SchedulerTempTaskInfrastructureStructMapping.instance.schedulerTempTaskToSchedulerTempTaskDO(schedulerTempTask);
        if (schedulerTempTaskDO.getId() == null) {
            schedulerTempTaskDO.setAddControl(schedulerTempTask.getAddControl());
            SchedulerTempTaskDO add = iSchedulerTempTaskService.add(schedulerTempTaskDO);
            schedulerTempTask.setId(SchedulerTempTaskId.of(add.getId()));
            return add != null;
        }
        schedulerTempTaskDO.setUpdateControl(schedulerTempTask.getUpdateControl());
        SchedulerTempTaskDO update = iSchedulerTempTaskService.update(schedulerTempTaskDO);
        return update != null;
    }

    @Override
    public boolean delete(SchedulerTempTaskId schedulerTempTaskId) {
        return iSchedulerTempTaskService.deleteById(schedulerTempTaskId.getId());
    }

    @Override
    public boolean delete(SchedulerTempTaskId schedulerTempTaskId, IdCommand idCommand) {
        return iSchedulerTempTaskService.deleteById(idCommand);
    }

    @Autowired
    public void setISchedulerTempTaskService(ISchedulerTempTaskService iSchedulerTempTaskService) {
        this.iSchedulerTempTaskService = iSchedulerTempTaskService;
    }
}
