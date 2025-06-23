import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyIprGeograPrefix = '/admin/web/data_company_ipr_geogra'
/**
 * 添加企业知识产权地理标识
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyIprGeograPrefix + '/create',data)
}
/**
 * 删除企业知识产权地理标识
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyIprGeograPrefix + '/delete',{data: data})
}
/**
 * 更新企业知识产权地理标识
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyIprGeograPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyIprGeograPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyIprGeograPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyIprGeograPrefix + '/page',{params: data})
}

