import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let cmsContentViewRecordPrefix = '/admin/web/cms_content_view_record'
/**
 * 添加内容访问记录
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(cmsContentViewRecordPrefix + '/create',data)
}
/**
 * 删除内容访问记录
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(cmsContentViewRecordPrefix + '/delete',{data: data})
}
/**
 * 更新内容访问记录
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(cmsContentViewRecordPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(cmsContentViewRecordPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(cmsContentViewRecordPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(cmsContentViewRecordPrefix + '/page',{params: data})
}

