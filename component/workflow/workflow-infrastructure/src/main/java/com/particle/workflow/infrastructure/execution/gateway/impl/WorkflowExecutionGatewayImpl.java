package com.particle.workflow.infrastructure.execution.gateway.impl;

import com.particle.workflow.domain.execution.WorkflowExecution;
import com.particle.workflow.domain.execution.WorkflowExecutionId;
import com.particle.workflow.domain.execution.gateway.WorkflowExecutionGateway;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionService;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;
import com.particle.workflow.infrastructure.execution.structmapping.WorkflowExecutionInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 工作流执行实例 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Component
public class WorkflowExecutionGatewayImpl extends AbstractBaseGatewayImpl<WorkflowExecutionId,WorkflowExecution> implements WorkflowExecutionGateway {

    private IWorkflowExecutionService iWorkflowExecutionService;

    @Override
    public WorkflowExecution getById(WorkflowExecutionId workflowExecutionId) {
        WorkflowExecutionDO byId = iWorkflowExecutionService.getById(workflowExecutionId.getId());
        WorkflowExecution workflowExecution = DomainFactory.create(WorkflowExecution.class);
        workflowExecution = WorkflowExecutionInfrastructureStructMapping.instance. workflowExecutionDOToWorkflowExecution(workflowExecution,byId);
        return workflowExecution;
    }

    @Override
    public boolean doSave(WorkflowExecution workflowExecution) {
        WorkflowExecutionDO workflowExecutionDO = WorkflowExecutionInfrastructureStructMapping.instance.workflowExecutionToWorkflowExecutionDO(workflowExecution);
        if (workflowExecutionDO.getId() == null) {
            workflowExecutionDO.setAddControl(workflowExecution.getAddControl());
            WorkflowExecutionDO add = iWorkflowExecutionService.add(workflowExecutionDO);
            workflowExecution.setId(WorkflowExecutionId.of(add.getId()));
            return add != null;
        }
        workflowExecutionDO.setUpdateControl(workflowExecution.getUpdateControl());
        WorkflowExecutionDO update = iWorkflowExecutionService.update(workflowExecutionDO);
        return update != null;
    }

    @Override
    public boolean delete(WorkflowExecutionId workflowExecutionId) {
        return iWorkflowExecutionService.deleteById(workflowExecutionId.getId());
    }

    @Override
    public boolean delete(WorkflowExecutionId workflowExecutionId, IdCommand idCommand) {
        return iWorkflowExecutionService.deleteById(idCommand);
    }

    @Autowired
    public void setIWorkflowExecutionService(IWorkflowExecutionService iWorkflowExecutionService) {
        this.iWorkflowExecutionService = iWorkflowExecutionService;
    }
}
