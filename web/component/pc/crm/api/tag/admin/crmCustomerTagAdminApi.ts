import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let crmCustomerTagPrefix = '/admin/web/crm_customer_tag'
/**
 * 添加客户标签
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(crmCustomerTagPrefix + '/create',data)
}
/**
 * 删除客户标签
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(crmCustomerTagPrefix + '/delete',{data: data})
}
/**
 * 更新客户标签
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(crmCustomerTagPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(crmCustomerTagPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(crmCustomerTagPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(crmCustomerTagPrefix + '/page',{params: data})
}

