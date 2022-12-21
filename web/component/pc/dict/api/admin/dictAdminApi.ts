import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let dictPrefix = '/admin/web/dict'
/**
 * 添加字典
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dictPrefix + '/create',data)
}
/**
 * 删除字典
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dictPrefix + '/delete',{data: data})
}
/**
 * 更新字典
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dictPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dictPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dictPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dictPrefix + '/page',{params: data})
}

export default {create,remove,update,detailForUpdate,list,page}