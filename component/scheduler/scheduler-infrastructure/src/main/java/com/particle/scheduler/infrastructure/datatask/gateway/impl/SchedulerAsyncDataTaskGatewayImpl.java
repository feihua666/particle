package com.particle.scheduler.infrastructure.datatask.gateway.impl;

import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTask;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTaskId;
import com.particle.scheduler.domain.datatask.gateway.SchedulerAsyncDataTaskGateway;
import com.particle.scheduler.infrastructure.datatask.service.ISchedulerAsyncDataTaskService;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerAsyncDataTaskDO;
import com.particle.scheduler.infrastructure.datatask.structmapping.SchedulerAsyncDataTaskInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 任务计划异步任务数据 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Component
public class SchedulerAsyncDataTaskGatewayImpl extends AbstractBaseGatewayImpl<SchedulerAsyncDataTaskId,SchedulerAsyncDataTask> implements SchedulerAsyncDataTaskGateway {

    private ISchedulerAsyncDataTaskService iSchedulerAsyncDataTaskService;

    @Override
    public SchedulerAsyncDataTask getById(SchedulerAsyncDataTaskId schedulerAsyncDataTaskId) {
        SchedulerAsyncDataTaskDO byId = iSchedulerAsyncDataTaskService.getById(schedulerAsyncDataTaskId.getId());
        SchedulerAsyncDataTask schedulerAsyncDataTask = DomainFactory.create(SchedulerAsyncDataTask.class);
        schedulerAsyncDataTask = SchedulerAsyncDataTaskInfrastructureStructMapping.instance. schedulerAsyncDataTaskDOToSchedulerAsyncDataTask(schedulerAsyncDataTask,byId);
        return schedulerAsyncDataTask;
    }

    @Override
    public boolean doSave(SchedulerAsyncDataTask schedulerAsyncDataTask) {
        SchedulerAsyncDataTaskDO schedulerAsyncDataTaskDO = SchedulerAsyncDataTaskInfrastructureStructMapping.instance.schedulerAsyncDataTaskToSchedulerAsyncDataTaskDO(schedulerAsyncDataTask);
        if (schedulerAsyncDataTaskDO.getId() == null) {
            schedulerAsyncDataTaskDO.setAddControl(schedulerAsyncDataTask.getAddControl());
            SchedulerAsyncDataTaskDO add = iSchedulerAsyncDataTaskService.add(schedulerAsyncDataTaskDO);
            schedulerAsyncDataTask.setId(SchedulerAsyncDataTaskId.of(add.getId()));
            return add != null;
        }
        schedulerAsyncDataTaskDO.setUpdateControl(schedulerAsyncDataTask.getUpdateControl());
        SchedulerAsyncDataTaskDO update = iSchedulerAsyncDataTaskService.update(schedulerAsyncDataTaskDO);
        return update != null;
    }

    @Override
    public boolean delete(SchedulerAsyncDataTaskId schedulerAsyncDataTaskId) {
        return iSchedulerAsyncDataTaskService.deleteById(schedulerAsyncDataTaskId.getId());
    }

    @Override
    public boolean delete(SchedulerAsyncDataTaskId schedulerAsyncDataTaskId, IdCommand idCommand) {
        return iSchedulerAsyncDataTaskService.deleteById(idCommand);
    }

    @Autowired
    public void setISchedulerAsyncDataTaskService(ISchedulerAsyncDataTaskService iSchedulerAsyncDataTaskService) {
        this.iSchedulerAsyncDataTaskService = iSchedulerAsyncDataTaskService;
    }

    @Override
    public SchedulerAsyncDataTask getByUniqueIdentifier(String uniqueIdentifier) {
        SchedulerAsyncDataTaskDO byId = iSchedulerAsyncDataTaskService.getByUniqueIdentifier(uniqueIdentifier);
        SchedulerAsyncDataTask schedulerAsyncDataTask = DomainFactory.create(SchedulerAsyncDataTask.class);
        schedulerAsyncDataTask = SchedulerAsyncDataTaskInfrastructureStructMapping.instance. schedulerAsyncDataTaskDOToSchedulerAsyncDataTask(schedulerAsyncDataTask,byId);
        return schedulerAsyncDataTask;
    }
}
