import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let agiVectorStoreRawDocumentPrefix = '/admin/web/agi_vector_store_raw_document'
/**
 * 添加知识存储原始文档
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(agiVectorStoreRawDocumentPrefix + '/create',data)
}
/**
 * 删除知识存储原始文档
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(agiVectorStoreRawDocumentPrefix + '/delete',{data: data})
}

/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(agiVectorStoreRawDocumentPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(agiVectorStoreRawDocumentPrefix + '/page',{params: data})
}

/**
 * 嵌入（向量化）知识存储原始文档
 * @param data
 */
export const embedding = (data: IdParam): AxiosPromise => {
    return axios.post(agiVectorStoreRawDocumentPrefix + '/embedding',data)
}

/**
 * 重新嵌入（向量化）知识存储原始文档
 * @param data
 */
export const reEmbedding = (data: IdParam): AxiosPromise => {
    return axios.post(agiVectorStoreRawDocumentPrefix + '/reEmbedding',data)
}

