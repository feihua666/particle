import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let navigationSitePrefix = '/admin/web/navigation_site'
/**
 * 添加导航网站
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(navigationSitePrefix + '/create',data)
}
/**
 * 删除导航网站
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(navigationSitePrefix + '/delete',{data: data})
}
/**
 * 更新导航网站
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(navigationSitePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(navigationSitePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(navigationSitePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(navigationSitePrefix + '/page',{params: data})
}

