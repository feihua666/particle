import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

let workflowDefinitionPrefix = '/admin/web/workflow_definition'
/**
 * 添加工作流定义
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(workflowDefinitionPrefix + '/create',data)
}
/**
 * 删除工作流定义
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(workflowDefinitionPrefix + '/delete',{data: data})
}
/**
 * 更新工作流定义
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(workflowDefinitionPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(workflowDefinitionPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(workflowDefinitionPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(workflowDefinitionPrefix + '/page',{params: data})
}

