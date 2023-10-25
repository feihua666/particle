import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let usageCountConfigPrefix = '/admin/web/usage_count_config'
/**
 * 添加使用次数配置
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(usageCountConfigPrefix + '/create',data)
}
/**
 * 删除使用次数配置
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(usageCountConfigPrefix + '/delete',{data: data})
}
/**
 * 更新使用次数配置
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(usageCountConfigPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(usageCountConfigPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(usageCountConfigPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(usageCountConfigPrefix + '/page',{params: data})
}

