import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let usageCountRecordDetailPrefix = '/admin/web/usage_count_record_detail'

/**
 * 删除使用次数记录明细
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(usageCountRecordDetailPrefix + '/delete',{data: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(usageCountRecordDetailPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(usageCountRecordDetailPrefix + '/page',{params: data})
}

