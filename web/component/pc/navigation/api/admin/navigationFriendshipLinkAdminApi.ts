import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let navigationFriendshipLinkPrefix = '/admin/web/navigation_friendship_link'
/**
 * 添加导航友情链接
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(navigationFriendshipLinkPrefix + '/create',data)
}
/**
 * 删除导航友情链接
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(navigationFriendshipLinkPrefix + '/delete',{data: data})
}
/**
 * 更新导航友情链接
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(navigationFriendshipLinkPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(navigationFriendshipLinkPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(navigationFriendshipLinkPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(navigationFriendshipLinkPrefix + '/page',{params: data})
}

