package com.particle.scheduler.adapter.feign.client.datatask.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 任务计划异步任务数据远程调用
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@FeignClient(name = "${particle.feign-client.name.scheduler:scheduler}",path = "/rpc/scheduler_async_data_task")
public interface SchedulerAsyncDataTaskRpcFeignClient {









}
