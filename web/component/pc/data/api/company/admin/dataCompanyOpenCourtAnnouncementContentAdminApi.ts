import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyOpenCourtAnnouncementContentPrefix = '/admin/web/data_company_open_court_announcement_content'
/**
 * 添加企业开庭公告内容
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyOpenCourtAnnouncementContentPrefix + '/create',data)
}
/**
 * 删除企业开庭公告内容
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyOpenCourtAnnouncementContentPrefix + '/delete',{data: data})
}
/**
 * 更新企业开庭公告内容
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyOpenCourtAnnouncementContentPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyOpenCourtAnnouncementContentPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyOpenCourtAnnouncementContentPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyOpenCourtAnnouncementContentPrefix + '/page',{params: data})
}

