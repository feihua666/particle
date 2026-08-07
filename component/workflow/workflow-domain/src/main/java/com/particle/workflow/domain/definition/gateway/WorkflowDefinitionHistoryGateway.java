package com.particle.workflow.domain.definition.gateway;

import com.particle.workflow.domain.definition.WorkflowDefinitionHistory;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistoryId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 工作流定义历史 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
public interface WorkflowDefinitionHistoryGateway extends IBaseGateway<WorkflowDefinitionHistoryId,WorkflowDefinitionHistory> {
}
