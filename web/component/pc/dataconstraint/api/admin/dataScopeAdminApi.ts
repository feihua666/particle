import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let dataScopePrefix = '/admin/web/data_scope'
/**
 * 添加数据范围
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataScopePrefix + '/create',data)
}
/**
 * 删除数据范围
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataScopePrefix + '/delete',{data: data})
}
/**
 * 更新数据范围
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataScopePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataScopePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataScopePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataScopePrefix + '/page',{params: data})
}

/**
 * 获取自定义数据
 * @param data
 */
export const customData = (customDataUrl: string,data: anyObj): AxiosPromise => {
    return axios.get(customDataUrl,{params: data})
}
