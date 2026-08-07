import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

let workflowExecutionNodePrefix = '/admin/web/workflow_execution_node'
/**
 * 添加工作流节点执行实例
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(workflowExecutionNodePrefix + '/create',data)
}
/**
 * 删除工作流节点执行实例
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(workflowExecutionNodePrefix + '/delete',{data: data})
}
/**
 * 更新工作流节点执行实例
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(workflowExecutionNodePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(workflowExecutionNodePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(workflowExecutionNodePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(workflowExecutionNodePrefix + '/page',{params: data})
}

