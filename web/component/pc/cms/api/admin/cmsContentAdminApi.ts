import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let cmsContentPrefix = '/admin/web/cms_content'
/**
 * 添加内容
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(cmsContentPrefix + '/create',data)
}
/**
 * 删除内容
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(cmsContentPrefix + '/delete',{data: data})
}
/**
 * 更新内容
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(cmsContentPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(cmsContentPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(cmsContentPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(cmsContentPrefix + '/page',{params: data})
}

