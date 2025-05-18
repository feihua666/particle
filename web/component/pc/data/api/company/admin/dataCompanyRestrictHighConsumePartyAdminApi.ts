import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyRestrictHighConsumePartyPrefix = '/admin/web/data_company_restrict_high_consume_party'
/**
 * 添加企业限制高消费当事人
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyRestrictHighConsumePartyPrefix + '/create',data)
}
/**
 * 删除企业限制高消费当事人
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyRestrictHighConsumePartyPrefix + '/delete',{data: data})
}
/**
 * 更新企业限制高消费当事人
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyRestrictHighConsumePartyPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyRestrictHighConsumePartyPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyRestrictHighConsumePartyPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyRestrictHighConsumePartyPrefix + '/page',{params: data})
}

