import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../common/api/api";
import {getApiPrefix} from "../../../../../common/api/apiPrefixConfig";

let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_USER) + '/admin/web/user-identifier'
/**
 * 添加用户登录标识
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 绑定用户登录标识
 * @param data
 */
export const createBind = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/createBind',data)
}
/**
 * 删除用户登录标识
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
}
/**
 * 更新用户登录标识
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(prefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/page',{params: data})
}

