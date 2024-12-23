import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam} from "../../../../../common/api/api";

let opLogAuditDataPrefix = '/admin/web/op_log_audit_data'

/**
 * 删除操作日志审计数据
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(opLogAuditDataPrefix + '/delete',{data: data})
}

/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(opLogAuditDataPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(opLogAuditDataPrefix + '/page',{params: data})
}

