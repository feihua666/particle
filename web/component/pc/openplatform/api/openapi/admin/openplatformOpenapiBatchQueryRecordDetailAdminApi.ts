import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformOpenapiBatchQueryRecordDetailPrefix = '/admin/web/openplatform_openapi_batch_query_record_detail'
/**
 * 添加开放接口批量查询记录明细
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiBatchQueryRecordDetailPrefix + '/create',data)
}
/**
 * 删除开放接口批量查询记录明细
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformOpenapiBatchQueryRecordDetailPrefix + '/delete',{data: data})
}
/**
 * 更新开放接口批量查询记录明细
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformOpenapiBatchQueryRecordDetailPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformOpenapiBatchQueryRecordDetailPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiBatchQueryRecordDetailPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiBatchQueryRecordDetailPrefix + '/page',{params: data})
}

