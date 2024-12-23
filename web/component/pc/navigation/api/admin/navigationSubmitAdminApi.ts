import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let navigationSubmitPrefix = '/admin/web/navigation_submit'
/**
 * 添加导航提交
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(navigationSubmitPrefix + '/create',data)
}
/**
 * 删除导航提交
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(navigationSubmitPrefix + '/delete',{data: data})
}
/**
 * 更新导航提交
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(navigationSubmitPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(navigationSubmitPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(navigationSubmitPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(navigationSubmitPrefix + '/page',{params: data})
}

/**
 * 确认导航提交
 * @param data
 */
export const sureSubmit = (data: IdParam): AxiosPromise => {
    return axios.post(navigationSubmitPrefix + '/sureSubmit',data)
}
