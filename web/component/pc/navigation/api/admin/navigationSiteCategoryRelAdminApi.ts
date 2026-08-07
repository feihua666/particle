import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../common/api/api";

import {getApiPrefix} from "../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_NAVIGATION) + '/admin/web/navigation_site_category_rel'
/**
 * 添加导航网站分类关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 删除导航网站分类关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
}
/**
 * 更新导航网站分类关系
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(prefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/detail-for-update',{params: data})
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

export interface NavigationSiteAssignNavigationCategory{
    navigationSiteId: string,// 导航网站id
    checkedNavigationCategoryIds?: string[],// 选择的导航分类id
    uncheckedNavigationCategoryIds?: string[],// 未选择的导航分类id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 导航网站分配导航分类
 * @param data
 */
export const navigationSiteAssignNavigationCategory = (data: NavigationSiteAssignNavigationCategory): AxiosPromise => {
    return axios.post(prefix + '/navigationSite/assign/navigationCategory',data)
}
/**
 * 根据导航网站ID查询已分配的导航分类id
 * @param data
 */
export const queryNavigationCategoryIdsByNavigationSiteId = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/queryNavigationCategoryIdsByNavigationSiteId',{params: data})
}
/**
 * 清空导航网站下的所有导航分类
 * @param data
 */
export const deleteByNavigationSiteId = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/deleteByNavigationSiteId',{data: data})
}
export interface NavigationCategoryAssignNavigationSite{
    navigationCategoryId: string,// 导航分类id
    checkedNavigationSiteIds?: string[],// 选择的导航网站id
    uncheckedNavigationSiteIds?: string[],// 未选择的导航网站id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 导航分类分配导航网站
 * @param data
 */
export const navigationCategoryAssignNavigationSite = (data: NavigationCategoryAssignNavigationSite): AxiosPromise => {
    return axios.post(prefix + '/navigationCategory/assign/navigationSite',data)
}
/**
 * 根据导航分类ID查询已分配的导航网站id
 * @param data
 */
export const queryNavigationSiteIdsByNavigationCategoryId = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/queryNavigationSiteIdsByNavigationCategoryId',{params: data})
}
/**
 * 清空导航分类下的所有导航网站
 * @param data
 */
export const deleteByNavigationCategoryId = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/deleteByNavigationCategoryId',{data: data})
}
