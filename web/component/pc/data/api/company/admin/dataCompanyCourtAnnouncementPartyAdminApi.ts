import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyCourtAnnouncementPartyPrefix = '/admin/web/data_company_court_announcement_party'
/**
 * 添加企业法院公告当事人
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyCourtAnnouncementPartyPrefix + '/create',data)
}
/**
 * 删除企业法院公告当事人
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyCourtAnnouncementPartyPrefix + '/delete',{data: data})
}
/**
 * 更新企业法院公告当事人
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyCourtAnnouncementPartyPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyCourtAnnouncementPartyPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyCourtAnnouncementPartyPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyCourtAnnouncementPartyPrefix + '/page',{params: data})
}

