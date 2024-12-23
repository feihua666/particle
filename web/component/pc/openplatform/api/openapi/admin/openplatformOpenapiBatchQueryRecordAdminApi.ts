import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformOpenapiBatchQueryRecordPrefix = '/admin/web/openplatform_openapi_batch_query_record'
/**
 * 添加开放接口批量查询记录
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiBatchQueryRecordPrefix + '/create',data)
}
/**
 * 删除开放接口批量查询记录
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformOpenapiBatchQueryRecordPrefix + '/delete',{data: data})
}
/**
 * 更新开放接口批量查询记录
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformOpenapiBatchQueryRecordPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformOpenapiBatchQueryRecordPrefix + '/detail-for-update',{params: data})
}
/**
 * 详情
 * @param data
 */
export const detail = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformOpenapiBatchQueryRecordPrefix + '/detail',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiBatchQueryRecordPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiBatchQueryRecordPrefix + '/page',{params: data})
}

