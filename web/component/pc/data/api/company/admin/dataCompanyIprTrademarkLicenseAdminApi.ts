import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyIprTrademarkLicensePrefix = '/admin/web/data_company_ipr_trademark_license'
/**
 * 添加企业知识产权商标许可信息
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyIprTrademarkLicensePrefix + '/create',data)
}
/**
 * 删除企业知识产权商标许可信息
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyIprTrademarkLicensePrefix + '/delete',{data: data})
}
/**
 * 更新企业知识产权商标许可信息
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyIprTrademarkLicensePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyIprTrademarkLicensePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyIprTrademarkLicensePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyIprTrademarkLicensePrefix + '/page',{params: data})
}

