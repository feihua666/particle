import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyJudgmentDocumentPartyPrefix = '/admin/web/data_company_judgment_document_party'
/**
 * 添加企业裁判文书当事人
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyJudgmentDocumentPartyPrefix + '/create',data)
}
/**
 * 删除企业裁判文书当事人
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyJudgmentDocumentPartyPrefix + '/delete',{data: data})
}
/**
 * 更新企业裁判文书当事人
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyJudgmentDocumentPartyPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyJudgmentDocumentPartyPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyJudgmentDocumentPartyPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyJudgmentDocumentPartyPrefix + '/page',{params: data})
}

