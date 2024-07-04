import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let dataObjectPrefix = '/admin/web/data_object'
/**
 * 添加数据对象
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataObjectPrefix + '/create',data)
}
/**
 * 删除数据对象
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataObjectPrefix + '/delete',{data: data})
}
/**
 * 更新数据对象
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataObjectPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataObjectPrefix + '/detail-for-update',{params: data})
}
/**
 * 获取详情
 * @param data
 */
export const detail = (data: IdParam): AxiosPromise => {
    return axios.get(dataObjectPrefix + '/detail',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataObjectPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataObjectPrefix + '/page',{params: data})
}

