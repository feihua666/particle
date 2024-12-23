import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let crmCustomerTagRelPrefix = '/admin/web/crm_customer_tag_rel'
/**
 * 添加客户标签关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(crmCustomerTagRelPrefix + '/create',data)
}
/**
 * 删除客户标签关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(crmCustomerTagRelPrefix + '/delete',{data: data})
}
/**
 * 更新客户标签关系
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(crmCustomerTagRelPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(crmCustomerTagRelPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(crmCustomerTagRelPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(crmCustomerTagRelPrefix + '/page',{params: data})
}

