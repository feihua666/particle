import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformOpenapiRecordAppOpenapiMonthSummaryPrefix = '/admin/web/openplatform_openapi_record_app_openapi_month_summary'
/**
 * 添加开放平台应用开放接口月汇总
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiRecordAppOpenapiMonthSummaryPrefix + '/create',data)
}
/**
 * 删除开放平台应用开放接口月汇总
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformOpenapiRecordAppOpenapiMonthSummaryPrefix + '/delete',{data: data})
}
/**
 * 更新开放平台应用开放接口月汇总
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformOpenapiRecordAppOpenapiMonthSummaryPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordAppOpenapiMonthSummaryPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordAppOpenapiMonthSummaryPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordAppOpenapiMonthSummaryPrefix + '/page',{params: data})
}

