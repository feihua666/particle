package com.particle.workflow.infrastructure.definition.gateway.impl;

import com.particle.workflow.domain.definition.WorkflowDefinitionHistory;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistoryId;
import com.particle.workflow.domain.definition.gateway.WorkflowDefinitionHistoryGateway;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionHistoryService;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.workflow.infrastructure.definition.structmapping.WorkflowDefinitionHistoryInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 工作流定义历史 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Component
public class WorkflowDefinitionHistoryGatewayImpl extends AbstractBaseGatewayImpl<WorkflowDefinitionHistoryId,WorkflowDefinitionHistory> implements WorkflowDefinitionHistoryGateway {

    private IWorkflowDefinitionHistoryService iWorkflowDefinitionHistoryService;

    @Override
    public WorkflowDefinitionHistory getById(WorkflowDefinitionHistoryId workflowDefinitionHistoryId) {
        WorkflowDefinitionHistoryDO byId = iWorkflowDefinitionHistoryService.getById(workflowDefinitionHistoryId.getId());
        WorkflowDefinitionHistory workflowDefinitionHistory = DomainFactory.create(WorkflowDefinitionHistory.class);
        workflowDefinitionHistory = WorkflowDefinitionHistoryInfrastructureStructMapping.instance. workflowDefinitionHistoryDOToWorkflowDefinitionHistory(workflowDefinitionHistory,byId);
        return workflowDefinitionHistory;
    }

    @Override
    public boolean doSave(WorkflowDefinitionHistory workflowDefinitionHistory) {
        WorkflowDefinitionHistoryDO workflowDefinitionHistoryDO = WorkflowDefinitionHistoryInfrastructureStructMapping.instance.workflowDefinitionHistoryToWorkflowDefinitionHistoryDO(workflowDefinitionHistory);
        if (workflowDefinitionHistoryDO.getId() == null) {
            workflowDefinitionHistoryDO.setAddControl(workflowDefinitionHistory.getAddControl());
            WorkflowDefinitionHistoryDO add = iWorkflowDefinitionHistoryService.add(workflowDefinitionHistoryDO);
            workflowDefinitionHistory.setId(WorkflowDefinitionHistoryId.of(add.getId()));
            return add != null;
        }
        workflowDefinitionHistoryDO.setUpdateControl(workflowDefinitionHistory.getUpdateControl());
        WorkflowDefinitionHistoryDO update = iWorkflowDefinitionHistoryService.update(workflowDefinitionHistoryDO);
        return update != null;
    }

    @Override
    public boolean delete(WorkflowDefinitionHistoryId workflowDefinitionHistoryId) {
        return iWorkflowDefinitionHistoryService.deleteById(workflowDefinitionHistoryId.getId());
    }

    @Override
    public boolean delete(WorkflowDefinitionHistoryId workflowDefinitionHistoryId, IdCommand idCommand) {
        return iWorkflowDefinitionHistoryService.deleteById(idCommand);
    }

    @Autowired
    public void setIWorkflowDefinitionHistoryService(IWorkflowDefinitionHistoryService iWorkflowDefinitionHistoryService) {
        this.iWorkflowDefinitionHistoryService = iWorkflowDefinitionHistoryService;
    }
}
