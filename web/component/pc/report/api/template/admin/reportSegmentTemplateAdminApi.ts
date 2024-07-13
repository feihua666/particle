import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let reportSegmentTemplatePrefix = '/admin/web/report_segment_template'
/**
 * 添加报告片段模板
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(reportSegmentTemplatePrefix + '/create',data)
}
/**
 * 删除报告片段模板
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(reportSegmentTemplatePrefix + '/delete',{data: data})
}
/**
 * 更新报告片段模板
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(reportSegmentTemplatePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(reportSegmentTemplatePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(reportSegmentTemplatePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(reportSegmentTemplatePrefix + '/page',{params: data})
}
/**
 * 复制片段模板
 * @param data
 */
export const copy = (data: anyObj): AxiosPromise => {
    return axios.post(reportSegmentTemplatePrefix + '/copy',data)
}
/**
 * 刷新报告片段模板缓存
 * @param data
 */
export const refreshCache = (data: IdParam): AxiosPromise => {
    return axios.put(reportSegmentTemplatePrefix + '/refreshCache',data)
}