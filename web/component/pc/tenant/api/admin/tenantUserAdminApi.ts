import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let tenantUserPrefix = '/admin/web/tenant_user'
/**
 * 添加租户用户
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(tenantUserPrefix + '/create',data)
}
/**
 * 删除租户用户
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(tenantUserPrefix + '/delete',{data: data})
}
/**
 * 更新租户用户
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(tenantUserPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(tenantUserPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(tenantUserPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(tenantUserPrefix + '/page',{params: data})
}

