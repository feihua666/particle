import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

let crawlerDataStoreContentPrefix = '/admin/web/crawler_data_store_content'
/**
 * 添加爬虫结构数据存储内容
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(crawlerDataStoreContentPrefix + '/create',data)
}
/**
 * 删除爬虫结构数据存储内容
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(crawlerDataStoreContentPrefix + '/delete',{data: data})
}
/**
 * 更新爬虫结构数据存储内容
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(crawlerDataStoreContentPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(crawlerDataStoreContentPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(crawlerDataStoreContentPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(crawlerDataStoreContentPrefix + '/page',{params: data})
}

