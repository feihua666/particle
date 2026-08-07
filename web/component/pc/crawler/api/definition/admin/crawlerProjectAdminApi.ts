import axios, { type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

let crawlerProjectPrefix = '/admin/web/crawler_project'
/**
 * 添加爬虫项目
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(crawlerProjectPrefix + '/create',data)
}
/**
 * 删除爬虫项目
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(crawlerProjectPrefix + '/delete',{data: data})
}
/**
 * 更新爬虫项目
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(crawlerProjectPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(crawlerProjectPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(crawlerProjectPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(crawlerProjectPrefix + '/page',{params: data})
}

