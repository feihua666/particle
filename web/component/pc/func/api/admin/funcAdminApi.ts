import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let funcPrefix = '/admin/web/func'
/**
 * 添加功能
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(funcPrefix + '/create',data)
}
/**
 * 删除功能
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(funcPrefix + '/delete',{data: data})
}
/**
 * 更新功能
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(funcPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(funcPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(funcPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(funcPrefix + '/page',{params: data})
}

/**
 * 复制功能
 * @param data
 */
export const copy = (data: IdParam): AxiosPromise => {
    return axios.post(funcPrefix + '/copy',data)
}
