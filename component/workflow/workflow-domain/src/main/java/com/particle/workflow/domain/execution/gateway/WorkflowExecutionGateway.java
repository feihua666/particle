package com.particle.workflow.domain.execution.gateway;

import com.particle.workflow.domain.execution.WorkflowExecution;
import com.particle.workflow.domain.execution.WorkflowExecutionId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 工作流执行实例 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
public interface WorkflowExecutionGateway extends IBaseGateway<WorkflowExecutionId,WorkflowExecution> {
}
