import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let crmDeptPrefix = '/admin/web/crm_dept'
/**
 * 添加客户公司部门
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(crmDeptPrefix + '/create',data)
}
/**
 * 删除客户公司部门
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(crmDeptPrefix + '/delete',{data: data})
}
/**
 * 更新客户公司部门
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(crmDeptPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(crmDeptPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(crmDeptPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(crmDeptPrefix + '/page',{params: data})
}

