import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

import {getApiPrefix} from "../../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_TENANT) + '/admin/web/tenant_func'
/**
 * 添加租户功能菜单
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 删除租户功能菜单
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
}
/**
 * 更新租户功能菜单
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

export interface TenantAssignFunc{
    tenantId: string,// 租户id
    checkedFuncIds?: string[],// 选择的功能菜单id
    uncheckedFuncIds?: string[],// 未选择的功能菜单id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 租户分配功能菜单
 * @param data
 */
export const tenantAssignFunc = (data: TenantAssignFunc): AxiosPromise => {
    return axios.post(prefix + '/tenant/assign/func',data)
}
export interface QueryFuncIdsByTenantId extends IdParam{
    // 功能应用id
    funcApplicationId?: string
}
/**
 * 根据租户ID查询已分配的功能菜单id
 * @param data
 */
export const queryFuncIdsByTenantId = (data: QueryFuncIdsByTenantId): AxiosPromise => {
    return axios.get(prefix + '/queryFuncIdsByTenantId',{params: data})
}
