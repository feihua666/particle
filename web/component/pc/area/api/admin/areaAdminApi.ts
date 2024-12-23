import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let areaPrefix = '/admin/web/area'
/**
 * 添加区域
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(areaPrefix + '/create',data)
}
/**
 * 删除区域
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(areaPrefix + '/delete',{data: data})
}
/**
 * 更新区域
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(areaPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(areaPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(areaPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(areaPrefix + '/page',{params: data})
}

