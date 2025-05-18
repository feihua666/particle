import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyAnnualReportForeignInvestPrefix = '/admin/web/data_company_annual_report_foreign_invest'
/**
 * 添加企业年报对外投资
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyAnnualReportForeignInvestPrefix + '/create',data)
}
/**
 * 删除企业年报对外投资
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyAnnualReportForeignInvestPrefix + '/delete',{data: data})
}
/**
 * 更新企业年报对外投资
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyAnnualReportForeignInvestPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyAnnualReportForeignInvestPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyAnnualReportForeignInvestPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyAnnualReportForeignInvestPrefix + '/page',{params: data})
}

