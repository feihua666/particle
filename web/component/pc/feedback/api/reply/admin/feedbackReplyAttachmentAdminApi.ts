import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let feedbackReplyAttachmentPrefix = '/admin/web/feedback_reply_attachment'
/**
 * 添加意见反馈回复附件
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(feedbackReplyAttachmentPrefix + '/create',data)
}
/**
 * 删除意见反馈回复附件
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(feedbackReplyAttachmentPrefix + '/delete',{data: data})
}
/**
 * 更新意见反馈回复附件
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(feedbackReplyAttachmentPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(feedbackReplyAttachmentPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(feedbackReplyAttachmentPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(feedbackReplyAttachmentPrefix + '/page',{params: data})
}

