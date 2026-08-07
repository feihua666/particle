import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../global/common/tools/ObjectTools";
import type {IdParam, updateParam} from "../../../../../common/api/api";

import {getApiPrefix} from "../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_NAVIGATION) + '/admin/web/navigation_site_tag_rel'
/**
 * 添加导航网站标签关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 删除导航网站标签关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
}
/**
 * 更新导航网站标签关系
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

export interface NavigationSiteAssignNavigationSiteTag{
    navigationSiteId: string,// 网站id
    checkedNavigationSiteTagIds?: string[],// 选择的网站标签id
    uncheckedNavigationSiteTagIds?: string[],// 未选择的网站标签id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 网站分配网站标签
 * @param data
 */
export const navigationSiteAssignNavigationSiteTag = (data: NavigationSiteAssignNavigationSiteTag): AxiosPromise => {
    return axios.post(prefix + '/navigationSite/assign/navigationSiteTag',data)
}
/**
 * 根据网站ID查询已分配的网站标签id
 * @param data
 */
export const queryNavigationSiteTagIdsByNavigationSiteId = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/queryNavigationSiteTagIdsByNavigationSiteId',{params: data})
}
/**
 * 清空网站下的所有网站标签
 * @param data
 */
export const deleteByNavigationSiteId = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/deleteByNavigationSiteId',{data: data})
}
export interface NavigationSiteTagAssignNavigationSite{
    navigationSiteTagId: string,// 网站标签id
    checkedNavigationSiteIds?: string[],// 选择的网站id
    uncheckedNavigationSiteIds?: string[],// 未选择的网站id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 网站标签分配网站
 * @param data
 */
export const navigationSiteTagAssignNavigationSite = (data: NavigationSiteTagAssignNavigationSite): AxiosPromise => {
    return axios.post(prefix + '/navigationSiteTag/assign/navigationSite',data)
}
/**
 * 根据网站标签ID查询已分配的网站id
 * @param data
 */
export const queryNavigationSiteIdsByNavigationSiteTagId = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/queryNavigationSiteIdsByNavigationSiteTagId',{params: data})
}
/**
 * 清空网站标签下的所有网站
 * @param data
 */
export const deleteByNavigationSiteTagId = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/deleteByNavigationSiteTagId',{data: data})
}
