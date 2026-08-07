import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

let workflowProjectPrefix = '/admin/web/workflow_project'
/**
 * 添加工作流项目
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(workflowProjectPrefix + '/create',data)
}
/**
 * 删除工作流项目
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(workflowProjectPrefix + '/delete',{data: data})
}
/**
 * 更新工作流项目
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(workflowProjectPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(workflowProjectPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(workflowProjectPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(workflowProjectPrefix + '/page',{params: data})
}

