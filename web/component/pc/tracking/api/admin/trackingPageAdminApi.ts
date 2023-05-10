import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let trackingPagePrefix = '/admin/web/tracking_page'
/**
 * 添加埋点页面
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(trackingPagePrefix + '/create',data)
}
/**
 * 删除埋点页面
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(trackingPagePrefix + '/delete',{data: data})
}
/**
 * 更新埋点页面
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(trackingPagePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(trackingPagePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(trackingPagePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(trackingPagePrefix + '/page',{params: data})
}

