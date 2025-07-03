import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let cmsSitePrefix = '/admin/web/cms_site'
/**
 * 添加站点
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(cmsSitePrefix + '/create',data)
}
/**
 * 删除站点
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(cmsSitePrefix + '/delete',{data: data})
}
/**
 * 更新站点
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(cmsSitePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(cmsSitePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(cmsSitePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(cmsSitePrefix + '/page',{params: data})
}

