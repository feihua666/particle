import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let reportReportApiPrefix = '/admin/web/report_report_api'
/**
 * 添加报告接口
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(reportReportApiPrefix + '/create',data)
}
/**
 * 删除报告接口
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(reportReportApiPrefix + '/delete',{data: data})
}
/**
 * 更新报告接口
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(reportReportApiPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(reportReportApiPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(reportReportApiPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(reportReportApiPrefix + '/page',{params: data})
}

/**
 * 刷新报告接口缓存
 * @param data
 */
export const refreshCache = (data: anyObj): AxiosPromise => {
    return axios.put(reportReportApiPrefix + '/refreshCache',data)
}