import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let cmsSiteIndexViewRecordPrefix = '/admin/web/cms_site_index_view_record'
/**
 * 添加站点首页访问记录
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(cmsSiteIndexViewRecordPrefix + '/create',data)
}
/**
 * 删除站点首页访问记录
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(cmsSiteIndexViewRecordPrefix + '/delete',{data: data})
}
/**
 * 更新站点首页访问记录
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(cmsSiteIndexViewRecordPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(cmsSiteIndexViewRecordPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(cmsSiteIndexViewRecordPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(cmsSiteIndexViewRecordPrefix + '/page',{params: data})
}

