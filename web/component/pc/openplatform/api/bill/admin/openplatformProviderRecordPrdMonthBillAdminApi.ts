import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformProviderRecordPrdMonthBillPrefix = '/admin/web/openplatform_provider_record_prd_month_bill'
/**
 * 添加开放平台供应商月账单
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformProviderRecordPrdMonthBillPrefix + '/create',data)
}
/**
 * 删除开放平台供应商月账单
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformProviderRecordPrdMonthBillPrefix + '/delete',{data: data})
}
/**
 * 更新开放平台供应商月账单
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformProviderRecordPrdMonthBillPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformProviderRecordPrdMonthBillPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformProviderRecordPrdMonthBillPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformProviderRecordPrdMonthBillPrefix + '/page',{params: data})
}
