import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../../common/api/api";

let agiModelProviderPrefix = '/admin/web/agi_model_provider'
/**
 * 添加AI模型提供商
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(agiModelProviderPrefix + '/create',data)
}
/**
 * 删除AI模型提供商
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(agiModelProviderPrefix + '/delete',{data: data})
}
/**
 * 更新AI模型提供商
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(agiModelProviderPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(agiModelProviderPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(agiModelProviderPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(agiModelProviderPrefix + '/page',{params: data})
}

