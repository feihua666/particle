import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let systemConfigPrefix = '/admin/web/system_config'
/**
 * 添加系统参数配置
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(systemConfigPrefix + '/create',data)
}
/**
 * 删除系统参数配置
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(systemConfigPrefix + '/delete',{data: data})
}
/**
 * 更新系统参数配置
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(systemConfigPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(systemConfigPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(systemConfigPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(systemConfigPrefix + '/page',{params: data})
}

