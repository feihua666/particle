import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataCompanyDeliveryAnnouncementPartyPrefix = '/admin/web/data_company_delivery_announcement_party'
/**
 * 添加企业送达公告当事人
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataCompanyDeliveryAnnouncementPartyPrefix + '/create',data)
}
/**
 * 删除企业送达公告当事人
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataCompanyDeliveryAnnouncementPartyPrefix + '/delete',{data: data})
}
/**
 * 更新企业送达公告当事人
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataCompanyDeliveryAnnouncementPartyPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataCompanyDeliveryAnnouncementPartyPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyDeliveryAnnouncementPartyPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataCompanyDeliveryAnnouncementPartyPrefix + '/page',{params: data})
}

