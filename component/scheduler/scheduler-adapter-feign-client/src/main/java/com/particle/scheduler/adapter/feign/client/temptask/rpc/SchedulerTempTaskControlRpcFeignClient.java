package com.particle.scheduler.adapter.feign.client.temptask.rpc;

import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskControlApplicationService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 任务计划临时任务远程调用
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@FeignClient(name = "${particle.feign-client.name.scheduler:scheduler}",path = "/rpc/scheduler_temp_task")
public interface SchedulerTempTaskControlRpcFeignClient {

    /**
     * 启动一个临时任务
     * 该方法只会记录任务状态，不会执行任务逻辑
     * @param code
     * @param name
     * @return 返回任务运行记录的id
     */
    @PostMapping("/start")
    Long start(String code, String name);

    /**
     * 完成临时任务
     * @param id 任务运行记录的id
     * @param isHasError 是否有异常，如果有异常，异常的内容应该使用 {@link ISchedulerTempTaskControlApplicationService#log(String, Long, String)}记录
     * @param result 执行完成后的结果
     */
    @PostMapping("/finish")
    void finish(Long id,Boolean isHasError,String result);

    /**
     * 记录日志，记录运行记录的日志
     * @param level 日志级别，建议使用java的运行日志级别，如：info、error等
     * @param id 任务运行记录的id
     * @param message 日志内容
     */
    @PostMapping("/log")
    void log(String level, Long id, String message);

    @PostMapping("/logInfo")
    void logInfo(Long id, String message);

    @PostMapping("/logError")
    void logError(Long id, String message);

    /**
     * 检查是否允许运行开关状态，用来判断是否停止程序任务运行，在程序运行过程中，应该时刻调用该方法以检测是否允许运行，因为在后台管理界面上提供了开关，用来控制是否允许运行
     * @param id 任务运行记录的id
     * @return
     */
    @GetMapping("/checkIsAllowRunSwitch")
    boolean checkIsAllowRunSwitch(Long id);

}
