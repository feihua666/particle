package com.particle.particledemo.test.dataquery

/**
 * 这是一个使用dataquery执行异步任务的模板脚本，结合 scheduler 模块中的异步任务支持，方便的实现 dataquery 的异步任务
 * 对应 DataqueryAsyncDataTaskGetResultTemplate.groovy 任务处理后，获取结果
 */

import com.particle.global.dto.response.SingleResponse
import com.particle.global.exception.code.ErrorCodeGlobalEnum
import com.particle.global.tool.spring.SpringContextHolder
import com.particle.scheduler.client.datatask.api.ISchedulerAsyncDataTaskControlApplicationService

/**
 * 获取任务结果
 * data示例：{"uniqueIdentifier": "xxxxxx"}
 */
def dataTaskVO = getDataTaskVO(data,data.uniqueIdentifier)
return dataTaskVO
/**
 * 获取任务
 * @param param
 */
def getDataTaskVO(param,String uniqueIdentifier){
    def schedulerAsyncDataTaskControlApplicationService = SpringContextHolder.getBean(ISchedulerAsyncDataTaskControlApplicationService.class) as ISchedulerAsyncDataTaskControlApplicationService
    def schedulerDataTaskVO = schedulerAsyncDataTaskControlApplicationService.getDataTaskVO(null, uniqueIdentifier,true)
    if (schedulerDataTaskVO == null) {
        return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SCHEDULER_DATA_TASK_NOT_FOUND)
    }
    return SingleResponse.of(schedulerDataTaskVO)
}

