import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformAppOpenapiPrefix = '/admin/web/openplatform_app_openapi'
/**
 * 添加开放平台应用与开放接口配置
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformAppOpenapiPrefix + '/create',data)
}
/**
 * 删除开放平台应用与开放接口配置
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformAppOpenapiPrefix + '/delete',{data: data})
}
/**
 * 刷新开放平台应用配置的单个开放接口信息缓存
 * @param data
 */
export const refreshCache = (data: IdParam): AxiosPromise => {
    return axios.put(openplatformAppOpenapiPrefix + '/refreshCache',data)
}
/**
 * 更新开放平台应用与开放接口配置
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformAppOpenapiPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformAppOpenapiPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformAppOpenapiPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformAppOpenapiPrefix + '/page',{params: data})
}

