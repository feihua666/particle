package com.particle.workflow.adapter.feign.client.execution.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 工作流执行实例远程调用
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@FeignClient(name = "${particle.feign-client.name.workflow:workflow}",path = "/rpc/workflow_execution")
public interface WorkflowExecutionRpcFeignClient {









}
