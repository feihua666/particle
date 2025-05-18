import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyVcFinancingInvestInstitutionRelPrefix = '/admin/web/data_company_vc_financing_invest_institution_rel'
/**
 * 添加企业融资历史投资机构关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/create',data)
}
/**
 * 删除企业融资历史投资机构关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/delete',{data: data})
}
/**
 * 更新企业融资历史投资机构关系
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/page',{params: data})
}

export interface CompanyVcFinancingAssignCompanyVcInvestInstitution{
    companyVcFinancingId: string,// 企业融资表IDid
    checkedCompanyVcInvestInstitutionIds?: string[],// 选择的企业投资机构表id
    uncheckedCompanyVcInvestInstitutionIds?: string[],// 未选择的企业投资机构表id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 企业融资表ID分配企业投资机构表
 * @param data
 */
export const companyVcFinancingAssignCompanyVcInvestInstitution = (data: CompanyVcFinancingAssignCompanyVcInvestInstitution): AxiosPromise => {
    return axios.post(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/companyVcFinancing/assign/companyVcInvestInstitution',data)
}
/**
 * 根据企业融资表IDID查询已分配的企业投资机构表id
 * @param data
 */
export const queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/queryCompanyVcInvestInstitutionIdsByCompanyVcFinancingId',{params: data})
}
/**
 * 清空企业融资表ID下的所有企业投资机构表
 * @param data
 */
export const deleteByCompanyVcFinancingId = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/deleteByCompanyVcFinancingId',{data: data})
}
export interface CompanyVcInvestInstitutionAssignCompanyVcFinancing{
    companyVcInvestInstitutionId: string,// 企业投资机构表id
    checkedCompanyVcFinancingIds?: string[],// 选择的企业融资表IDid
    uncheckedCompanyVcFinancingIds?: string[],// 未选择的企业融资表IDid,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 企业投资机构表分配企业融资表ID
 * @param data
 */
export const companyVcInvestInstitutionAssignCompanyVcFinancing = (data: CompanyVcInvestInstitutionAssignCompanyVcFinancing): AxiosPromise => {
    return axios.post(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/companyVcInvestInstitution/assign/companyVcFinancing',data)
}
/**
 * 根据企业投资机构表ID查询已分配的企业融资表IDid
 * @param data
 */
export const queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/queryCompanyVcFinancingIdsByCompanyVcInvestInstitutionId',{params: data})
}
/**
 * 清空企业投资机构表下的所有企业融资表ID
 * @param data
 */
export const deleteByCompanyVcInvestInstitutionId = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyVcFinancingInvestInstitutionRelPrefix + '/deleteByCompanyVcInvestInstitutionId',{data: data})
}
