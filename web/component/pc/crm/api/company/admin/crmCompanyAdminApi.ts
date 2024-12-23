import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let crmCompanyPrefix = '/admin/web/crm_company'
/**
 * 添加客户公司
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(crmCompanyPrefix + '/create',data)
}
/**
 * 删除客户公司
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(crmCompanyPrefix + '/delete',{data: data})
}
/**
 * 更新客户公司
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(crmCompanyPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(crmCompanyPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(crmCompanyPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(crmCompanyPrefix + '/page',{params: data})
}

