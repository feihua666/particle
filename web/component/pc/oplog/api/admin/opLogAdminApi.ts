import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let opLogPrefix = '/admin/web/op_log'

/**
 * 删除操作日志
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(opLogPrefix + '/delete',{data: data})
}

/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(opLogPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(opLogPrefix + '/page',{params: data})
}

