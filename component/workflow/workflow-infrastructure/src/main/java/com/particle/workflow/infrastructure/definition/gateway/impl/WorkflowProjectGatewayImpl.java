package com.particle.workflow.infrastructure.definition.gateway.impl;

import com.particle.workflow.domain.definition.WorkflowProject;
import com.particle.workflow.domain.definition.WorkflowProjectId;
import com.particle.workflow.domain.definition.gateway.WorkflowProjectGateway;
import com.particle.workflow.infrastructure.definition.service.IWorkflowProjectService;
import com.particle.workflow.infrastructure.definition.dos.WorkflowProjectDO;
import com.particle.workflow.infrastructure.definition.structmapping.WorkflowProjectInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 工作流项目 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Component
public class WorkflowProjectGatewayImpl extends AbstractBaseGatewayImpl<WorkflowProjectId,WorkflowProject> implements WorkflowProjectGateway {

    private IWorkflowProjectService iWorkflowProjectService;

    @Override
    public WorkflowProject getById(WorkflowProjectId workflowProjectId) {
        WorkflowProjectDO byId = iWorkflowProjectService.getById(workflowProjectId.getId());
        WorkflowProject workflowProject = DomainFactory.create(WorkflowProject.class);
        workflowProject = WorkflowProjectInfrastructureStructMapping.instance. workflowProjectDOToWorkflowProject(workflowProject,byId);
        return workflowProject;
    }

    @Override
    public boolean doSave(WorkflowProject workflowProject) {
        WorkflowProjectDO workflowProjectDO = WorkflowProjectInfrastructureStructMapping.instance.workflowProjectToWorkflowProjectDO(workflowProject);
        if (workflowProjectDO.getId() == null) {
            workflowProjectDO.setAddControl(workflowProject.getAddControl());
            WorkflowProjectDO add = iWorkflowProjectService.add(workflowProjectDO);
            workflowProject.setId(WorkflowProjectId.of(add.getId()));
            return add != null;
        }
        workflowProjectDO.setUpdateControl(workflowProject.getUpdateControl());
        WorkflowProjectDO update = iWorkflowProjectService.update(workflowProjectDO);
        return update != null;
    }

    @Override
    public boolean delete(WorkflowProjectId workflowProjectId) {
        return iWorkflowProjectService.deleteById(workflowProjectId.getId());
    }

    @Override
    public boolean delete(WorkflowProjectId workflowProjectId, IdCommand idCommand) {
        return iWorkflowProjectService.deleteById(idCommand);
    }

    @Autowired
    public void setIWorkflowProjectService(IWorkflowProjectService iWorkflowProjectService) {
        this.iWorkflowProjectService = iWorkflowProjectService;
    }
}
