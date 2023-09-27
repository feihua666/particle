import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let lowcodeSegmentGenPrefix = '/admin/web/lowcode-segment-gen'
/**
 * 添加片段生成数据
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(lowcodeSegmentGenPrefix + '/create',data)
}
/**
 * 删除片段生成数据
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(lowcodeSegmentGenPrefix + '/delete',{data: data})
}
/**
 * 更新片段生成数据
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(lowcodeSegmentGenPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(lowcodeSegmentGenPrefix + '/detail-for-update',{params: data})
}
/**
 * 数据详情
 * @param data
 */
export const detail = (data: IdParam): AxiosPromise => {
    return axios.get(lowcodeSegmentGenPrefix + '/detail',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(lowcodeSegmentGenPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(lowcodeSegmentGenPrefix + '/page',{params: data})
}
/**
 * 重新加载模型json数据
 * @param data
 */
export const reloadLowcodeModelJson = (data: IdParam): AxiosPromise => {
    return axios.put(lowcodeSegmentGenPrefix + '/reloadLowcodeModelJson',data)
}
/**
 * 低代码生成设计和渲染
 * @param data
 */
export const renderGen = (data: anyObj): AxiosPromise => {
    return axios.post(lowcodeSegmentGenPrefix + '/renderGen',data)
}