package com.particle.workflow.domain.execution.gateway;

import com.particle.workflow.domain.execution.WorkflowExecutionNode;
import com.particle.workflow.domain.execution.WorkflowExecutionNodeId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 工作流节点执行实例 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
public interface WorkflowExecutionNodeGateway extends IBaseGateway<WorkflowExecutionNodeId,WorkflowExecutionNode> {
}
