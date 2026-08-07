import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

let crawlerExecutionPrefix = '/admin/web/crawler_execution'
/**
 * 添加爬虫执行实例
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(crawlerExecutionPrefix + '/create',data)
}
/**
 * 删除爬虫执行实例
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(crawlerExecutionPrefix + '/delete',{data: data})
}
/**
 * 更新爬虫执行实例
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(crawlerExecutionPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(crawlerExecutionPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(crawlerExecutionPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(crawlerExecutionPrefix + '/page',{params: data})
}

/**
 * 添加爬虫执行实例
 * @param data
 */
export const execute = (data: anyObj): AxiosPromise => {
    return axios.post(crawlerExecutionPrefix + '/execute',data)
}
