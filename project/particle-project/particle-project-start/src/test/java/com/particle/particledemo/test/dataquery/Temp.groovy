/**
 * 这是一个使用dataquery执行异步任务的模板脚本，结合 scheduler 模块中的异步任务支持，方便的实现 dataquery 的异步任务
 */


import cn.hutool.core.lang.UUID
import com.particle.dataquery.app.dataapi.executor.representation.DataQueryDataApiDataApiQueryCommandExecutor
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand
import com.particle.global.dto.response.SingleResponse
import com.particle.global.tool.json.JsonTool
import com.particle.global.tool.spring.SpringContextHolder
import com.particle.scheduler.client.datatask.api.ISchedulerAsyncDataTaskControlApplicationService
import com.particle.scheduler.client.datatask.dto.data.SchedulerDataTaskVO
import org.slf4j.LoggerFactory

import java.time.LocalDateTime

def log = LoggerFactory.getLogger("dataqueryAsyncDataTask.technology_innovation_indicator")

def groupIdentifier = "科创指标异步任务"
//提交异步任务，获取任务唯一标识
def schedulerDataTaskVO = submit(data,groupIdentifier)
def uniqueIdentifier = schedulerDataTaskVO.getUniqueIdentifier()

dataQueryDataApiExecutor.execute {
    // 标识任务开始
    processStart(data,uniqueIdentifier)
    try {
        // 处理任务
        String result = handle(data,uniqueIdentifier)
        // 任务正常完成
        finish(uniqueIdentifier,false,null,result)
    } catch (Exception e) {
        log.error(groupIdentifier + ",任务异常完成,uniqueIdentifier=" + uniqueIdentifier,e)
        // 任务异常完成
        finish(uniqueIdentifier,true,e.getMessage(),null)
    }

}

return SingleResponse.of(schedulerDataTaskVO)

/**
 * 提交任务
 * @param param
 * @return
 */
def SchedulerDataTaskVO submit(param,String groupIdentifier){
    def uniqueIdentifier = UUID.fastUUID().toString(true)
    def params = JsonTool.toJsonStr(param)
    def expireInDays = 7

    def schedulerAsyncDataTaskControlApplicationService = SpringContextHolder.getBean(ISchedulerAsyncDataTaskControlApplicationService.class) as ISchedulerAsyncDataTaskControlApplicationService
    def schedulerDataTaskVO = schedulerAsyncDataTaskControlApplicationService.submitAndGetDataTaskVO(groupIdentifier,
            uniqueIdentifier,
            params,
            LocalDateTime.now().plusDays(expireInDays))
    return schedulerDataTaskVO
}
/**
 * 处理任务
 * @param param
 */
def processStart(param,String uniqueIdentifier){
    def schedulerAsyncDataTaskControlApplicationService = SpringContextHolder.getBean(ISchedulerAsyncDataTaskControlApplicationService.class) as ISchedulerAsyncDataTaskControlApplicationService
    schedulerAsyncDataTaskControlApplicationService.processStart(null,uniqueIdentifier)
}
/**
 * 完成任务
 * @param param
 */
def finish(String uniqueIdentifier,boolean isError,String errorMessage,String result){
    def schedulerAsyncDataTaskControlApplicationService = SpringContextHolder.getBean(ISchedulerAsyncDataTaskControlApplicationService.class) as ISchedulerAsyncDataTaskControlApplicationService
    schedulerAsyncDataTaskControlApplicationService.finish(null,uniqueIdentifier,isError,errorMessage,result)
}

/**
 * 处理任务
 * @param param
 */
def String handle(param,String uniqueIdentifier){
    def result = null

    def command = new DataQueryDataApiQueryCommand()
    command.setUrl("/technology_innovation_indicator")
    command.setParam(param)
    def dataQueryDataApiDataApiQueryCommandExecutor = SpringContextHolder.getBean(DataQueryDataApiDataApiQueryCommandExecutor.class) as DataQueryDataApiDataApiQueryCommandExecutor
    def response = dataQueryDataApiDataApiQueryCommandExecutor.dataApiQuery(command) as SingleResponse

    if (response.getData() != null) {
        result = JsonTool.toJsonStr(response.getData())
    }

    return result
}
