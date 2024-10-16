import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformOpenapiRecordAppOpenapiDayRtSummaryPrefix = '/admin/web/openplatform_openapi_record_app_openapi_day_rt_summary'
/**
 * 添加开放平台应用开放接口日实时汇总
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiRecordAppOpenapiDayRtSummaryPrefix + '/create',data)
}
/**
 * 删除开放平台应用开放接口日实时汇总
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformOpenapiRecordAppOpenapiDayRtSummaryPrefix + '/delete',{data: data})
}
/**
 * 更新开放平台应用开放接口日实时汇总
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformOpenapiRecordAppOpenapiDayRtSummaryPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordAppOpenapiDayRtSummaryPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordAppOpenapiDayRtSummaryPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordAppOpenapiDayRtSummaryPrefix + '/page',{params: data})
}

