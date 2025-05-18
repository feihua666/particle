import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyAnnualReportEquityChangePrefix = '/admin/web/data_company_annual_report_equity_change'
/**
 * 添加企业年报股权变更
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyAnnualReportEquityChangePrefix + '/create',data)
}
/**
 * 删除企业年报股权变更
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyAnnualReportEquityChangePrefix + '/delete',{data: data})
}
/**
 * 更新企业年报股权变更
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyAnnualReportEquityChangePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyAnnualReportEquityChangePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyAnnualReportEquityChangePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyAnnualReportEquityChangePrefix + '/page',{params: data})
}

