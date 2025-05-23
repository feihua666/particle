/**
 * 这是一个使用dataquery执行任务的模板脚本，结合 scheduler 模块中的临时任务支持，方便的实现 dataquery 的临时任务
 */
import com.particle.global.tool.spring.SpringContextHolder
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskControlApplicationService

def schedulerTempTaskControlApplicationService = SpringContextHolder.getBean(ISchedulerTempTaskControlApplicationService.class)
def taskId = schedulerTempTaskControlApplicationService.start("templateCode", "templateName")

def isHasError = false
try {
    schedulerTempTaskControlApplicationService.logInfo(taskId,"start")
    doTask(data, dataQueryDataApiExecutor, datasourceApi,schedulerTempTaskControlApplicationService,taskId)
    schedulerTempTaskControlApplicationService.logInfo(taskId,"complete")
} catch (Exception e) {
    isHasError = true
    schedulerTempTaskControlApplicationService.logError(taskId,"error=" + e.getMessage())
} finally {

    schedulerTempTaskControlApplicationService.finish(taskId, isHasError)
}

/**
 * 执行任务
 * @param data
 * @param dataQueryDataApiExecutor
 * @param datasourceApi
 */
public static void doTask(data, dataQueryDataApiExecutor, datasourceApi,
                          ISchedulerTempTaskControlApplicationService schedulerTempTaskControlApplicationService,taskId) {
    while (true) {
        if (!schedulerTempTaskControlApplicationService.checkIsAllowRunSwitch(taskId)) {
            schedulerTempTaskControlApplicationService.logInfo(taskId,"任务被暂停，退出不再执行")
        }
    // todo 执行任务
    }
}