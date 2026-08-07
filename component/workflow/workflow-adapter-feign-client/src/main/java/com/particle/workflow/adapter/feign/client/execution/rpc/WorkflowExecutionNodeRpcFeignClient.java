package com.particle.workflow.adapter.feign.client.execution.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 工作流节点执行实例远程调用
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@FeignClient(name = "${particle.feign-client.name.workflow:workflow}",path = "/rpc/workflow_execution_node")
public interface WorkflowExecutionNodeRpcFeignClient {









}
