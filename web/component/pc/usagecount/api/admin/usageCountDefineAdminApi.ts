import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let usageCountDefinePrefix = '/admin/web/usage_count_define'
/**
 * 添加使用次数定义
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(usageCountDefinePrefix + '/create',data)
}
/**
 * 删除使用次数定义
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(usageCountDefinePrefix + '/delete',{data: data})
}
/**
 * 更新使用次数定义
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(usageCountDefinePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(usageCountDefinePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(usageCountDefinePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(usageCountDefinePrefix + '/page',{params: data})
}

