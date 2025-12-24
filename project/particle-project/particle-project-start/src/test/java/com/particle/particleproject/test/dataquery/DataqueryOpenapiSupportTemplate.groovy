import com.particle.dataquery.app.dataapi.executor.representation.DataQueryDataApiDataApiQueryCommandExecutor
import com.particle.global.openapi.endpoint.command.OpenapiCommand
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand
import com.particle.global.tool.spring.SpringContextHolder

def param = data
def dataQueryDataApiDataApiQueryCommandExecutor = SpringContextHolder.getBean(DataQueryDataApiDataApiQueryCommandExecutor.class)
// 查询
if (param instanceof OpenapiCommand) {
    return doOpenapiCommand(param)
}
// 入库
else if (param instanceof OpenapiWarehouseCommand) {
    return doOpenapiWarehouseCommand(param)
}
// 其它情况，非开放平台情况
else{
    return doOther(param)
}
// 开放平台查询处理
def doOpenapiCommand(OpenapiCommand param) {

}
// 入库处理
def doOpenapiWarehouseCommand(OpenapiWarehouseCommand param) {

}
// 其它情况处理
def doOther(param) {

}
