import axios, { type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

let crawlerDefinitionPrefix = '/admin/web/crawler_definition'
/**
 * 添加爬虫定义
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(crawlerDefinitionPrefix + '/create',data)
}
/**
 * 删除爬虫定义
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(crawlerDefinitionPrefix + '/delete',{data: data})
}
/**
 * 更新爬虫定义
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(crawlerDefinitionPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(crawlerDefinitionPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(crawlerDefinitionPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(crawlerDefinitionPrefix + '/page',{params: data})
}

