package com.particle.scheduler.adapter.feign.client.datatask.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 任务计划任务数据远程调用
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@FeignClient(name = "${particle.feign-client.name.scheduler:scheduler}",path = "/rpc/scheduler_job_data_task")
public interface SchedulerJobDataTaskRpcFeignClient {









}
