import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dynamicDataCategoryPrefix = '/admin/web/dynamic_data_category'
/**
 * 添加动态数据分类
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dynamicDataCategoryPrefix + '/create',data)
}
/**
 * 删除动态数据分类
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dynamicDataCategoryPrefix + '/delete',{data: data})
}
/**
 * 更新动态数据分类
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dynamicDataCategoryPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dynamicDataCategoryPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dynamicDataCategoryPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dynamicDataCategoryPrefix + '/page',{params: data})
}

