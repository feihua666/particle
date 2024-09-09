package com.particle.scheduler.adapter.feign.client.schedule.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 任务计划执行记录远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@FeignClient(name = "${particle.feign-client.name.scheduler:scheduler}",path = "/rpc/scheduler_execute_record")
public interface SchedulerExecuteRecordRpcFeignClient {









}
