import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformOpenapiRecordCustomerMonthBillPrefix = '/admin/web/openplatform_openapi_record_customer_month_bill'
/**
 * 添加开放平台客户月账单
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiRecordCustomerMonthBillPrefix + '/create',data)
}
/**
 * 删除开放平台客户月账单
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformOpenapiRecordCustomerMonthBillPrefix + '/delete',{data: data})
}
/**
 * 更新开放平台客户月账单
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformOpenapiRecordCustomerMonthBillPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordCustomerMonthBillPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordCustomerMonthBillPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordCustomerMonthBillPrefix + '/page',{params: data})
}

/**
 * 统计上月开放平台客户月账单
 * @param data
 */
export const lastMonthBillStatistic = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiRecordCustomerMonthBillPrefix + '/lastMonthStatistic',data)
}
/**
 * 统计本月开放平台客户月账单
 * @param data
 */
export const thisMonthBillStatistic = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiRecordCustomerMonthBillPrefix + '/thisMonthStatistic',data)
}