import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformProviderApiPrefix = '/admin/web/openplatform_provider_api'
/**
 * 添加开放平台供应商接口
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformProviderApiPrefix + '/create',data)
}
/**
 * 删除开放平台供应商接口
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformProviderApiPrefix + '/delete',{data: data})
}
/**
 * 更新开放平台供应商接口
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformProviderApiPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformProviderApiPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformProviderApiPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformProviderApiPrefix + '/page',{params: data})
}

