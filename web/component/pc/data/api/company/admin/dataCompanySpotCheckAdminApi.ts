import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanySpotCheckPrefix = '/admin/web/data_company_spot_check'
/**
 * 添加企业抽查检查
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanySpotCheckPrefix + '/create',data)
}
/**
 * 删除企业抽查检查
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanySpotCheckPrefix + '/delete',{data: data})
}
/**
 * 更新企业抽查检查
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanySpotCheckPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanySpotCheckPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanySpotCheckPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanySpotCheckPrefix + '/page',{params: data})
}

