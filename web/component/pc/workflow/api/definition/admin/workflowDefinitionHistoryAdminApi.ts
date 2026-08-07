import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

let workflowDefinitionHistoryPrefix = '/admin/web/workflow_definition_history'
/**
 * 添加工作流定义历史
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(workflowDefinitionHistoryPrefix + '/create',data)
}
/**
 * 删除工作流定义历史
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(workflowDefinitionHistoryPrefix + '/delete',{data: data})
}
/**
 * 更新工作流定义历史
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(workflowDefinitionHistoryPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(workflowDefinitionHistoryPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(workflowDefinitionHistoryPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(workflowDefinitionHistoryPrefix + '/page',{params: data})
}

/**
 * 添加工作流定义历史草稿
 * @param data
 */
export const createDraft = (data: anyObj): AxiosPromise => {
    return axios.post(workflowDefinitionHistoryPrefix + '/createDraft',data)
}
/**
 * 详情数据
 * @param data
 */
export const detail = (data: IdParam): AxiosPromise => {
    return axios.get(workflowDefinitionHistoryPrefix + '/detail',{params: data})
}
