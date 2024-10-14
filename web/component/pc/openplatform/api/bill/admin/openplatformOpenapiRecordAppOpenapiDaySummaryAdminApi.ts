import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformOpenapiRecordAppOpenapiDaySummaryPrefix = '/admin/web/openplatform_openapi_record_app_openapi_day_summary'
/**
 * 添加开放平台应用开放接口日汇总
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiRecordAppOpenapiDaySummaryPrefix + '/create',data)
}
/**
 * 删除开放平台应用开放接口日汇总
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformOpenapiRecordAppOpenapiDaySummaryPrefix + '/delete',{data: data})
}
/**
 * 更新开放平台应用开放接口日汇总
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformOpenapiRecordAppOpenapiDaySummaryPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordAppOpenapiDaySummaryPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordAppOpenapiDaySummaryPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordAppOpenapiDaySummaryPrefix + '/page',{params: data})
}

/**
 * 统计昨日开放平台应用开放接口日汇总
 * @param data
 */
export const yesterdayStatistic = (): AxiosPromise => {
    return axios.post(openplatformOpenapiRecordAppOpenapiDaySummaryPrefix + '/yesterdayStatistic')
}
/**
 * 统计今日开放平台应用开放接口日汇总
 * @param data
 */
export const todayStatistic = (): AxiosPromise => {
    return axios.post(openplatformOpenapiRecordAppOpenapiDaySummaryPrefix + '/todayStatistic')
}