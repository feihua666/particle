import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanySeriousIllegalPrefix = '/admin/web/data_company_serious_illegal'
/**
 * 添加企业严重违法
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanySeriousIllegalPrefix + '/create',data)
}
/**
 * 删除企业严重违法
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanySeriousIllegalPrefix + '/delete',{data: data})
}
/**
 * 更新企业严重违法
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanySeriousIllegalPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanySeriousIllegalPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanySeriousIllegalPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanySeriousIllegalPrefix + '/page',{params: data})
}

