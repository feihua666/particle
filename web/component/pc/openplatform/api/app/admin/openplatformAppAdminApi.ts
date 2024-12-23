import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformAppPrefix = '/admin/web/openplatform_app'
/**
 * 添加开放平台应用
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformAppPrefix + '/create',data)
}
/**
 * 删除开放平台应用
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformAppPrefix + '/delete',{data: data})
}
/**
 * 更新开放平台应用
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformAppPrefix + '/update',data)
}
/**
 * 刷新开放平台应用缓存
 * @param data
 */
export const refreshCache = (data: IdParam): AxiosPromise => {
    return axios.put(openplatformAppPrefix + '/refreshCache',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformAppPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformAppPrefix + '/list',{params: data})
}
/**
 * 列表，没有分页，查询登录用户全部数据
 * @param data
 */
export const listCurrentUser = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformAppPrefix + '/list_by_you',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformAppPrefix + '/page',{params: data})
}

