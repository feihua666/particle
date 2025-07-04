import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let cmsContentMultimediaPrefix = '/admin/web/cms_content_multimedia'
/**
 * 添加内容多媒体
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(cmsContentMultimediaPrefix + '/create',data)
}
/**
 * 删除内容多媒体
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(cmsContentMultimediaPrefix + '/delete',{data: data})
}
/**
 * 更新内容多媒体
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(cmsContentMultimediaPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(cmsContentMultimediaPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(cmsContentMultimediaPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(cmsContentMultimediaPrefix + '/page',{params: data})
}

