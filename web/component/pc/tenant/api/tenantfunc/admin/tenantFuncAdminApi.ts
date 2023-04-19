import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let tenantFuncPrefix = '/admin/web/tenant_func'
/**
 * 添加租户功能菜单
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(tenantFuncPrefix + '/create',data)
}
/**
 * 删除租户功能菜单
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(tenantFuncPrefix + '/delete',{data: data})
}
/**
 * 更新租户功能菜单
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(tenantFuncPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(tenantFuncPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(tenantFuncPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(tenantFuncPrefix + '/page',{params: data})
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
    return axios.post(tenantFuncPrefix + '/tenant/assign/func',data)
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
    return axios.get(tenantFuncPrefix + '/queryFuncIdsByTenantId',{params: data})
}