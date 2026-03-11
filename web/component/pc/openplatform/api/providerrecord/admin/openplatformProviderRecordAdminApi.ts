import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam} from "../../../../../../common/api/api";

import {getApiPrefix} from "../../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_OPEN_PLATFORM) + '/admin/web/openplatform_provider_record'

let openplatformProviderRecordParamPrefix = '/admin/web/openplatform_provider_record_param'

/**
 * 删除开放平台开放接口供应商调用记录
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
}

/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/page',{params: data})
}

/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const paramDetailByOpenplatformProviderRecordId = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformProviderRecordParamPrefix + '/detailByOpenplatformProviderRecordId',{params: data})
}
