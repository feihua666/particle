import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam} from "../../../../../../common/api/api";

let opLogErrorPrefix = '/admin/web/op_log_error'
/**
 * 添加操作异常日志
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(opLogErrorPrefix + '/create',data)
}
/**
 * 删除操作异常日志
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(opLogErrorPrefix + '/delete',{data: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(opLogErrorPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(opLogErrorPrefix + '/page',{params: data})
}

