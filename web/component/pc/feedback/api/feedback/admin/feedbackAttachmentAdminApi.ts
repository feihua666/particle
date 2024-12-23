import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let feedbackAttachmentPrefix = '/admin/web/feedback_attachment'
/**
 * 添加意见反馈附件
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(feedbackAttachmentPrefix + '/create',data)
}
/**
 * 删除意见反馈附件
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(feedbackAttachmentPrefix + '/delete',{data: data})
}
/**
 * 更新意见反馈附件
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(feedbackAttachmentPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(feedbackAttachmentPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(feedbackAttachmentPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(feedbackAttachmentPrefix + '/page',{params: data})
}

