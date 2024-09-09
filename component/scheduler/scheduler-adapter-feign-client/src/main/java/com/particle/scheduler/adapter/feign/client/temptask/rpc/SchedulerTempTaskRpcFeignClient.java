package com.particle.scheduler.adapter.feign.client.temptask.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 任务计划临时任务远程调用
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@FeignClient(name = "${particle.feign-client.name.scheduler:scheduler}",path = "/rpc/scheduler_temp_task")
public interface SchedulerTempTaskRpcFeignClient {









}
