import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataSourcePrefix = '/admin/web/lowcode-segment-template'
/**
 * 添加片段模板
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataSourcePrefix + '/create',data)
}
/**
 * 删除片段模板
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataSourcePrefix + '/delete',{data: data})
}
/**
 * 更新片段模板
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataSourcePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataSourcePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataSourcePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataSourcePrefix + '/page',{params: data})
}

/**
 * 片段模板渲染测试
 * @param data
 */
export const renderTest = (data: anyObj): AxiosPromise => {
    return axios.post(dataSourcePrefix + '/renderTest',data)
}

/**
 * 复制片段模板
 * @param data
 */
export const copy = (data: anyObj): AxiosPromise => {
    return axios.post(dataSourcePrefix + '/copy',data)
}