import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

import {getApiPrefix} from "../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_DATA_CONSTRAINT) + '/admin/web/data_scope_custom_data_rel'
/**
 * 添加数据范围自定义数据关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 删除数据范围自定义数据关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
}
/**
 * 更新数据范围自定义数据关系
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
export interface DataScopeAssignCustomData{
    dataScopeId: string,// 数据范围id
    checkedDataIds?: string[],// 选择的自定义数据id
    uncheckedDataIds?: string[],// 未选择的自定义数据id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 数据范围分配自定义数据
 * @param data
 */
export const dataScopeAssignCustomData = (data: DataScopeAssignCustomData): AxiosPromise => {
    return axios.post(prefix + '/dataScope/assign/customData',data)
}
/**
 * 根据数据范围ID查询已分配的自定义数据id
 * @param data
 */
export const queryCustomDataIdsByDataScopeId = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/queryCustomDataIdsByDataScopeId',{params: data})
}
/**
 * 清空数据范围下的所有自定义数据
 * @param data
 */
export const deleteByDataScopeId = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/deleteByDataScopeId',{data: data})
}
