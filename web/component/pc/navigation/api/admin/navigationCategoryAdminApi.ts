import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let navigationCategoryPrefix = '/admin/web/navigation_category'
/**
 * 添加导航分类
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(navigationCategoryPrefix + '/create',data)
}
/**
 * 删除导航分类
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(navigationCategoryPrefix + '/delete',{data: data})
}
/**
 * 更新导航分类
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(navigationCategoryPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(navigationCategoryPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(navigationCategoryPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(navigationCategoryPrefix + '/page',{params: data})
}

