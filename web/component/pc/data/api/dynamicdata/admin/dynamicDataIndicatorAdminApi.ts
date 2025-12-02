import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dynamicDataIndicatorPrefix = '/admin/web/dynamic_data_indicator'
/**
 * 添加动态数据指标
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dynamicDataIndicatorPrefix + '/create',data)
}
/**
 * 删除动态数据指标
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dynamicDataIndicatorPrefix + '/delete',{data: data})
}
/**
 * 更新动态数据指标
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dynamicDataIndicatorPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dynamicDataIndicatorPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dynamicDataIndicatorPrefix + '/list',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * 不适合全部查询，建议传dynamicDataIndicatorCategoryId限制数据量
 * @param data
 */
export const listWithDynamicTableField = (data: anyObj): AxiosPromise => {
    return axios.get(dynamicDataIndicatorPrefix + '/listWithDynamicTableField',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dynamicDataIndicatorPrefix + '/page',{params: data})
}

