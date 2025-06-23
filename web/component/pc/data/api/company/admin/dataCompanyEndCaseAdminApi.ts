import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyEndCasePrefix = '/admin/web/data_company_end_case'
/**
 * 添加企业终本案件
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyEndCasePrefix + '/create',data)
}
/**
 * 删除企业终本案件
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyEndCasePrefix + '/delete',{data: data})
}
/**
 * 更新企业终本案件
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyEndCasePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyEndCasePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyEndCasePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyEndCasePrefix + '/page',{params: data})
}

