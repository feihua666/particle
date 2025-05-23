package com.particle.scheduler.infrastructure.datatask.gateway.impl;

import com.particle.scheduler.domain.datatask.SchedulerJobDataTask;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTaskId;
import com.particle.scheduler.domain.datatask.gateway.SchedulerJobDataTaskGateway;
import com.particle.scheduler.infrastructure.datatask.service.ISchedulerJobDataTaskService;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerJobDataTaskDO;
import com.particle.scheduler.infrastructure.datatask.structmapping.SchedulerJobDataTaskInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 任务计划任务数据 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Component
public class SchedulerJobDataTaskGatewayImpl extends AbstractBaseGatewayImpl<SchedulerJobDataTaskId,SchedulerJobDataTask> implements SchedulerJobDataTaskGateway {

    private ISchedulerJobDataTaskService iSchedulerJobDataTaskService;

    @Override
    public SchedulerJobDataTask getById(SchedulerJobDataTaskId schedulerJobDataTaskId) {
        SchedulerJobDataTaskDO byId = iSchedulerJobDataTaskService.getById(schedulerJobDataTaskId.getId());
        SchedulerJobDataTask schedulerJobDataTask = DomainFactory.create(SchedulerJobDataTask.class);
        schedulerJobDataTask = SchedulerJobDataTaskInfrastructureStructMapping.instance. schedulerJobDataTaskDOToSchedulerJobDataTask(schedulerJobDataTask,byId);
        return schedulerJobDataTask;
    }

    @Override
    public boolean doSave(SchedulerJobDataTask schedulerJobDataTask) {
        SchedulerJobDataTaskDO schedulerJobDataTaskDO = SchedulerJobDataTaskInfrastructureStructMapping.instance.schedulerJobDataTaskToSchedulerJobDataTaskDO(schedulerJobDataTask);
        if (schedulerJobDataTaskDO.getId() == null) {
            schedulerJobDataTaskDO.setAddControl(schedulerJobDataTask.getAddControl());
            SchedulerJobDataTaskDO add = iSchedulerJobDataTaskService.add(schedulerJobDataTaskDO);
            schedulerJobDataTask.setId(SchedulerJobDataTaskId.of(add.getId()));
            return add != null;
        }
        schedulerJobDataTaskDO.setUpdateControl(schedulerJobDataTask.getUpdateControl());
        SchedulerJobDataTaskDO update = iSchedulerJobDataTaskService.update(schedulerJobDataTaskDO);
        return update != null;
    }

    @Override
    public boolean delete(SchedulerJobDataTaskId schedulerJobDataTaskId) {
        return iSchedulerJobDataTaskService.deleteById(schedulerJobDataTaskId.getId());
    }

    @Override
    public boolean delete(SchedulerJobDataTaskId schedulerJobDataTaskId, IdCommand idCommand) {
        return iSchedulerJobDataTaskService.deleteById(idCommand);
    }

    @Autowired
    public void setISchedulerJobDataTaskService(ISchedulerJobDataTaskService iSchedulerJobDataTaskService) {
        this.iSchedulerJobDataTaskService = iSchedulerJobDataTaskService;
    }
}
