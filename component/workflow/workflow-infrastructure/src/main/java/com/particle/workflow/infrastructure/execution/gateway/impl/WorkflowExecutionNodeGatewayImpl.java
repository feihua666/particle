package com.particle.workflow.infrastructure.execution.gateway.impl;

import com.particle.workflow.domain.execution.WorkflowExecutionNode;
import com.particle.workflow.domain.execution.WorkflowExecutionNodeId;
import com.particle.workflow.domain.execution.gateway.WorkflowExecutionNodeGateway;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionNodeService;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;
import com.particle.workflow.infrastructure.execution.structmapping.WorkflowExecutionNodeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 工作流节点执行实例 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Component
public class WorkflowExecutionNodeGatewayImpl extends AbstractBaseGatewayImpl<WorkflowExecutionNodeId,WorkflowExecutionNode> implements WorkflowExecutionNodeGateway {

    private IWorkflowExecutionNodeService iWorkflowExecutionNodeService;

    @Override
    public WorkflowExecutionNode getById(WorkflowExecutionNodeId workflowExecutionNodeId) {
        WorkflowExecutionNodeDO byId = iWorkflowExecutionNodeService.getById(workflowExecutionNodeId.getId());
        WorkflowExecutionNode workflowExecutionNode = DomainFactory.create(WorkflowExecutionNode.class);
        workflowExecutionNode = WorkflowExecutionNodeInfrastructureStructMapping.instance. workflowExecutionNodeDOToWorkflowExecutionNode(workflowExecutionNode,byId);
        return workflowExecutionNode;
    }

    @Override
    public boolean doSave(WorkflowExecutionNode workflowExecutionNode) {
        WorkflowExecutionNodeDO workflowExecutionNodeDO = WorkflowExecutionNodeInfrastructureStructMapping.instance.workflowExecutionNodeToWorkflowExecutionNodeDO(workflowExecutionNode);
        if (workflowExecutionNodeDO.getId() == null) {
            workflowExecutionNodeDO.setAddControl(workflowExecutionNode.getAddControl());
            WorkflowExecutionNodeDO add = iWorkflowExecutionNodeService.add(workflowExecutionNodeDO);
            workflowExecutionNode.setId(WorkflowExecutionNodeId.of(add.getId()));
            return add != null;
        }
        workflowExecutionNodeDO.setUpdateControl(workflowExecutionNode.getUpdateControl());
        WorkflowExecutionNodeDO update = iWorkflowExecutionNodeService.update(workflowExecutionNodeDO);
        return update != null;
    }

    @Override
    public boolean delete(WorkflowExecutionNodeId workflowExecutionNodeId) {
        return iWorkflowExecutionNodeService.deleteById(workflowExecutionNodeId.getId());
    }

    @Override
    public boolean delete(WorkflowExecutionNodeId workflowExecutionNodeId, IdCommand idCommand) {
        return iWorkflowExecutionNodeService.deleteById(idCommand);
    }

    @Autowired
    public void setIWorkflowExecutionNodeService(IWorkflowExecutionNodeService iWorkflowExecutionNodeService) {
        this.iWorkflowExecutionNodeService = iWorkflowExecutionNodeService;
    }
}
