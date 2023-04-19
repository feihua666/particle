import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let funcApplicationPrefix = '/admin/web/func_application'
/**
 * 添加功能应用
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(funcApplicationPrefix + '/create',data)
}
/**
 * 删除功能应用
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(funcApplicationPrefix + '/delete',{data: data})
}
/**
 * 更新功能应用
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(funcApplicationPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(funcApplicationPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(funcApplicationPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(funcApplicationPrefix + '/page',{params: data})
}

