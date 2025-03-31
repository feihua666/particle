import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let agiAgentPrefix = '/admin/web/agi_agent'
/**
 * 添加智能体
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(agiAgentPrefix + '/create',data)
}
/**
 * 删除智能体
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(agiAgentPrefix + '/delete',{data: data})
}
/**
 * 更新智能体
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(agiAgentPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(agiAgentPrefix + '/detail-for-update',{params: data})
}
/**
 * 查看时使用，加载要查看的数据
 * @param data
 */
export const detail = (data: IdParam): AxiosPromise => {
    return axios.get(agiAgentPrefix + '/detail',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(agiAgentPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(agiAgentPrefix + '/page',{params: data})
}

