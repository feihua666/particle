import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

import {getApiPrefix} from "../../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_REPORT) + '/admin/web/report_report_api'
/**
 * 添加报告接口
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 删除报告接口
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
}
/**
 * 更新报告接口
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(prefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/detail-for-update',{params: data})
}
/**
 * 查看详情
 * @param data
 */
export const detail = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/detail',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/page',{params: data})
}

/**
 * 刷新报告接口缓存
 * @param data
 */
export const refreshCache = (data: anyObj): AxiosPromise => {
    return axios.put(prefix + '/refreshCache',data)
}
/**
 * 测试数据查询数据源接口
 * @param data
 */
const reportReportApiTestPrefix = prefix + '/test'
export interface ApiTestParam{
    // 接口地址
    url: string,
    // 数据
    param?: any,
    queryString?: string
}
/**
 * 报告接口测试
 * 参见后端接口：com.particle.report.adapter.reportapi.web.admin.ReportReportApiAdminWebTestController.reportApiTest
 * @param data
 */
export const test = (data: ApiTestParam): AxiosPromise => {
    return axios.post(reportReportApiTestPrefix + '/api_test',data,{timeout: 15 * 60 * 1000})
}
