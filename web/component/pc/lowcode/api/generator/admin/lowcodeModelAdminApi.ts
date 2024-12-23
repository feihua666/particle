import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let lowcodeModelPrefix = '/admin/web/lowcode-model'
/**
 * 添加模型
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(lowcodeModelPrefix + '/create',data)
}
/**
 * 删除模型
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(lowcodeModelPrefix + '/delete',{data: data})
}
/**
 * 更新模型
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(lowcodeModelPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(lowcodeModelPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(lowcodeModelPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(lowcodeModelPrefix + '/page',{params: data})
}
/**
 * 根据模型和数据源装载模型项
 * @param data
 */
export const loadByModelAndDatasource = (data: anyObj): AxiosPromise => {
    return axios.post(lowcodeModelPrefix + '/loadByModelAndDatasource',data)
}
