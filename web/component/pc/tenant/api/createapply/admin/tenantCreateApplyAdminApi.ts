import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let tenantCreateApplyPrefix = '/admin/web/tenant_create_apply'
/**
 * 添加租户创建申请
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(tenantCreateApplyPrefix + '/create',data)
}
/**
 * 删除租户创建申请
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(tenantCreateApplyPrefix + '/delete',{data: data})
}
/**
 * 更新租户创建申请
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(tenantCreateApplyPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(tenantCreateApplyPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(tenantCreateApplyPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(tenantCreateApplyPrefix + '/page',{params: data})
}

/**
 * 审核租户创建申请
 * @param data
 */
export const audit = (data: updateParam): AxiosPromise => {
    return axios.put(tenantCreateApplyPrefix + '/audit',data)
}
