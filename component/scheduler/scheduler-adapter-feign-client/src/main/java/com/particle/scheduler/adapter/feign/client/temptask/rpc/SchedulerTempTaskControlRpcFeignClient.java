package com.particle.scheduler.adapter.feign.client.temptask.rpc;

import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskControlApplicationService;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskFinishCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskLogBaseCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskLogCommand;
import com.particle.scheduler.client.temptask.dto.command.control.SchedulerTempTaskStartCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 任务计划临时任务远程调用
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@FeignClient(name = "${particle.feign-client.scheduler.name:scheduler-start}", contextId = "schedulerTempTaskControlRpcFeignClient", url = "${particle.feign-client.scheduler.url:}", path = "/rpc/scheduler_temp_task")
public interface SchedulerTempTaskControlRpcFeignClient {

    /**
     * 启动一个临时任务
     * 该方法只会记录任务状态，不会执行任务逻辑
     * @param schedulerTempTaskStartCommand
     * @return 返回任务运行记录的id
     */
    @PostMapping("/start")
    SingleResponse<Long> start(@RequestBody SchedulerTempTaskStartCommand schedulerTempTaskStartCommand);

    /**
     * 完成临时任务
     * @param schedulerTempTaskFinishCommand
     */
    @PostMapping("/finish")
    public Response finish(@RequestBody SchedulerTempTaskFinishCommand schedulerTempTaskFinishCommand);

    /**
     * 记录日志，记录运行记录的日志
     * @param schedulerTempTaskLogCommand
     */
    @PostMapping("/log")
    Response log(@RequestBody SchedulerTempTaskLogCommand schedulerTempTaskLogCommand);

    @PostMapping("/logInfo")
    Response logInfo(@RequestBody SchedulerTempTaskLogBaseCommand schedulerTempTaskLogBaseCommand);

    @PostMapping("/logError")
    Response logError(@RequestBody SchedulerTempTaskLogBaseCommand schedulerTempTaskLogBaseCommand);

    /**
     * 检查是否允许运行开关状态，用来判断是否停止程序任务运行，在程序运行过程中，应该时刻调用该方法以检测是否允许运行，因为在后台管理界面上提供了开关，用来控制是否允许运行
     * @param id 任务运行记录的id
     * @return
     */
    @GetMapping("/checkIsAllowRunSwitch")
    Response checkIsAllowRunSwitch(Long id);

}
