package com.particle.workflow.infrastructure.definition.gateway.impl;

import com.particle.workflow.domain.definition.WorkflowDefinition;
import com.particle.workflow.domain.definition.WorkflowDefinitionId;
import com.particle.workflow.domain.definition.gateway.WorkflowDefinitionGateway;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionService;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.workflow.infrastructure.definition.structmapping.WorkflowDefinitionInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 工作流定义 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Component
public class WorkflowDefinitionGatewayImpl extends AbstractBaseGatewayImpl<WorkflowDefinitionId,WorkflowDefinition> implements WorkflowDefinitionGateway {

    private IWorkflowDefinitionService iWorkflowDefinitionService;

    @Override
    public WorkflowDefinition getById(WorkflowDefinitionId workflowDefinitionId) {
        WorkflowDefinitionDO byId = iWorkflowDefinitionService.getById(workflowDefinitionId.getId());
        WorkflowDefinition workflowDefinition = DomainFactory.create(WorkflowDefinition.class);
        workflowDefinition = WorkflowDefinitionInfrastructureStructMapping.instance. workflowDefinitionDOToWorkflowDefinition(workflowDefinition,byId);
        return workflowDefinition;
    }

    @Override
    public boolean doSave(WorkflowDefinition workflowDefinition) {
        WorkflowDefinitionDO workflowDefinitionDO = WorkflowDefinitionInfrastructureStructMapping.instance.workflowDefinitionToWorkflowDefinitionDO(workflowDefinition);
        if (workflowDefinitionDO.getId() == null) {
            workflowDefinitionDO.setAddControl(workflowDefinition.getAddControl());
            WorkflowDefinitionDO add = iWorkflowDefinitionService.add(workflowDefinitionDO);
            workflowDefinition.setId(WorkflowDefinitionId.of(add.getId()));
            return add != null;
        }
        workflowDefinitionDO.setUpdateControl(workflowDefinition.getUpdateControl());
        WorkflowDefinitionDO update = iWorkflowDefinitionService.update(workflowDefinitionDO);
        return update != null;
    }

    @Override
    public boolean delete(WorkflowDefinitionId workflowDefinitionId) {
        return iWorkflowDefinitionService.deleteById(workflowDefinitionId.getId());
    }

    @Override
    public boolean delete(WorkflowDefinitionId workflowDefinitionId, IdCommand idCommand) {
        return iWorkflowDefinitionService.deleteById(idCommand);
    }

    @Autowired
    public void setIWorkflowDefinitionService(IWorkflowDefinitionService iWorkflowDefinitionService) {
        this.iWorkflowDefinitionService = iWorkflowDefinitionService;
    }
}
