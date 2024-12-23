import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformProviderRecordPrdApiMonthSummaryPrefix = '/admin/web/openplatform_provider_record_prd_api_month_summary'
/**
 * 添加开放平台供应商接口月汇总
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformProviderRecordPrdApiMonthSummaryPrefix + '/create',data)
}
/**
 * 删除开放平台供应商接口月汇总
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformProviderRecordPrdApiMonthSummaryPrefix + '/delete',{data: data})
}
/**
 * 更新开放平台供应商接口月汇总
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformProviderRecordPrdApiMonthSummaryPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformProviderRecordPrdApiMonthSummaryPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformProviderRecordPrdApiMonthSummaryPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformProviderRecordPrdApiMonthSummaryPrefix + '/page',{params: data})
}

