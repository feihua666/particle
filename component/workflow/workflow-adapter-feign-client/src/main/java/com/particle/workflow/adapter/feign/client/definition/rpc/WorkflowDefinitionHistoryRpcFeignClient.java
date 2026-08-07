package com.particle.workflow.adapter.feign.client.definition.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 工作流定义历史远程调用
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@FeignClient(name = "${particle.feign-client.name.workflow:workflow}",path = "/rpc/workflow_definition_history")
public interface WorkflowDefinitionHistoryRpcFeignClient {









}
