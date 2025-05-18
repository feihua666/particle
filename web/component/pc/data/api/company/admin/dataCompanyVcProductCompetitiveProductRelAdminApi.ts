import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyVcProductCompetitiveProductRelPrefix = '/admin/web/data_company_vc_product_competitive_product_rel'
/**
 * 添加企业融资产品竞品关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyVcProductCompetitiveProductRelPrefix + '/create',data)
}
/**
 * 删除企业融资产品竞品关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyVcProductCompetitiveProductRelPrefix + '/delete',{data: data})
}
/**
 * 更新企业融资产品竞品关系
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyVcProductCompetitiveProductRelPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyVcProductCompetitiveProductRelPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyVcProductCompetitiveProductRelPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyVcProductCompetitiveProductRelPrefix + '/page',{params: data})
}

export interface CompanyVcProductAssignCompanyVcCompetitiveProduct{
    companyVcProductId: string,// 企业融资产品表IDid
    checkedCompanyVcCompetitiveProductIds?: string[],// 选择的企业竞品id
    uncheckedCompanyVcCompetitiveProductIds?: string[],// 未选择的企业竞品id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 企业融资产品表ID分配企业竞品
 * @param data
 */
export const companyVcProductAssignCompanyVcCompetitiveProduct = (data: CompanyVcProductAssignCompanyVcCompetitiveProduct): AxiosPromise => {
    return axios.post(dataCompanyVcProductCompetitiveProductRelPrefix + '/companyVcProduct/assign/companyVcCompetitiveProduct',data)
}
/**
 * 根据企业融资产品表IDID查询已分配的企业竞品id
 * @param data
 */
export const queryCompanyVcCompetitiveProductIdsByCompanyVcProductId = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyVcProductCompetitiveProductRelPrefix + '/queryCompanyVcCompetitiveProductIdsByCompanyVcProductId',{params: data})
}
/**
 * 清空企业融资产品表ID下的所有企业竞品
 * @param data
 */
export const deleteByCompanyVcProductId = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyVcProductCompetitiveProductRelPrefix + '/deleteByCompanyVcProductId',{data: data})
}
export interface CompanyVcCompetitiveProductAssignCompanyVcProduct{
    companyVcCompetitiveProductId: string,// 企业竞品id
    checkedCompanyVcProductIds?: string[],// 选择的企业融资产品表IDid
    uncheckedCompanyVcProductIds?: string[],// 未选择的企业融资产品表IDid,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 企业竞品分配企业融资产品表ID
 * @param data
 */
export const companyVcCompetitiveProductAssignCompanyVcProduct = (data: CompanyVcCompetitiveProductAssignCompanyVcProduct): AxiosPromise => {
    return axios.post(dataCompanyVcProductCompetitiveProductRelPrefix + '/companyVcCompetitiveProduct/assign/companyVcProduct',data)
}
/**
 * 根据企业竞品ID查询已分配的企业融资产品表IDid
 * @param data
 */
export const queryCompanyVcProductIdsByCompanyVcCompetitiveProductId = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyVcProductCompetitiveProductRelPrefix + '/queryCompanyVcProductIdsByCompanyVcCompetitiveProductId',{params: data})
}
/**
 * 清空企业竞品下的所有企业融资产品表ID
 * @param data
 */
export const deleteByCompanyVcCompetitiveProductId = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyVcProductCompetitiveProductRelPrefix + '/deleteByCompanyVcCompetitiveProductId',{data: data})
}
