import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let crmCustomerContactPrefix = '/admin/web/crm_customer_contact'
/**
 * 添加客户联系方式
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(crmCustomerContactPrefix + '/create',data)
}
/**
 * 删除客户联系方式
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(crmCustomerContactPrefix + '/delete',{data: data})
}
/**
 * 更新客户联系方式
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(crmCustomerContactPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(crmCustomerContactPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(crmCustomerContactPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(crmCustomerContactPrefix + '/page',{params: data})
}

