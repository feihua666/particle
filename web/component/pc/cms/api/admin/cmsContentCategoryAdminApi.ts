import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let cmsContentCategoryPrefix = '/admin/web/cms_content_category'
/**
 * 添加内容分类
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(cmsContentCategoryPrefix + '/create',data)
}
/**
 * 删除内容分类
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(cmsContentCategoryPrefix + '/delete',{data: data})
}
/**
 * 更新内容分类
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(cmsContentCategoryPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(cmsContentCategoryPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(cmsContentCategoryPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(cmsContentCategoryPrefix + '/page',{params: data})
}

