import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let cmsChannelViewRecordPrefix = '/admin/web/cms_channel_view_record'
/**
 * 添加栏目访问记录
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(cmsChannelViewRecordPrefix + '/create',data)
}
/**
 * 删除栏目访问记录
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(cmsChannelViewRecordPrefix + '/delete',{data: data})
}
/**
 * 更新栏目访问记录
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(cmsChannelViewRecordPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(cmsChannelViewRecordPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(cmsChannelViewRecordPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(cmsChannelViewRecordPrefix + '/page',{params: data})
}

