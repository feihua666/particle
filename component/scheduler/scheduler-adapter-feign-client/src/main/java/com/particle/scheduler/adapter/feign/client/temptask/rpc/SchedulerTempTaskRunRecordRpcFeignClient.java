package com.particle.scheduler.adapter.feign.client.temptask.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 任务计划临时任务运行记录远程调用
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@FeignClient(name = "${particle.feign-client.name.scheduler:scheduler}",path = "/rpc/scheduler_temp_task_run_record")
public interface SchedulerTempTaskRunRecordRpcFeignClient {









}
