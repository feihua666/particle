package com.particle.workflow.adapter.feign.client.definition.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 工作流定义远程调用
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@FeignClient(name = "${particle.feign-client.name.workflow:workflow}",path = "/rpc/workflow_definition")
public interface WorkflowDefinitionRpcFeignClient {









}
