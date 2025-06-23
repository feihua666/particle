import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyIprIntegratedCircuitPrefix = '/admin/web/data_company_ipr_integrated_circuit'
/**
 * 添加企业知识产权集成电路
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyIprIntegratedCircuitPrefix + '/create',data)
}
/**
 * 删除企业知识产权集成电路
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyIprIntegratedCircuitPrefix + '/delete',{data: data})
}
/**
 * 更新企业知识产权集成电路
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyIprIntegratedCircuitPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyIprIntegratedCircuitPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyIprIntegratedCircuitPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyIprIntegratedCircuitPrefix + '/page',{params: data})
}

