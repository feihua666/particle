package com.particle.workflow.domain.definition.gateway;

import com.particle.workflow.domain.definition.WorkflowDefinition;
import com.particle.workflow.domain.definition.WorkflowDefinitionId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 工作流定义 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
public interface WorkflowDefinitionGateway extends IBaseGateway<WorkflowDefinitionId,WorkflowDefinition> {
}
