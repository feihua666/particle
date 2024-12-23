import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let messagePrefix = '/admin/web/message'
/**
 * 添加消息
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(messagePrefix + '/create',data)
}
/**
 * 删除消息
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(messagePrefix + '/delete',{data: data})
}
/**
 * 更新消息
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(messagePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(messagePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(messagePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(messagePrefix + '/page',{params: data})
}

