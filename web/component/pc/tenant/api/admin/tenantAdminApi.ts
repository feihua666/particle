import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let tenantPrefix = '/admin/web/tenant'
/**
 * 添加租户
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(tenantPrefix + '/create',data)
}
/**
 * 删除租户
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(tenantPrefix + '/delete',{data: data})
}
/**
 * 更新租户
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(tenantPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(tenantPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(tenantPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(tenantPrefix + '/page',{params: data})
}

/**
 * 一键添加租户创建申请并审批通过
 * @param data
 */
export const oneClickCreate = (data: anyObj): AxiosPromise => {
    return axios.post(tenantPrefix + '/oneClickCreate',data)
}