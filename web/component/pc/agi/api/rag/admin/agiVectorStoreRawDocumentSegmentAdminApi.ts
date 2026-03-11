import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

import {getApiPrefix} from "../../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_AGI) + '/admin/web/agi_vector_store_raw_document_segment'
/**
 * 添加知识存储原始文档片段
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 删除知识存储原始文档片段
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
}
/**
 * 更新知识存储原始文档片段
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
 * 嵌入（向量化）知识存储原始文档片段
 * @param data
 */
export const embedding = (data: IdParam): AxiosPromise => {
    return axios.post(prefix + '/embedding',data)
}
