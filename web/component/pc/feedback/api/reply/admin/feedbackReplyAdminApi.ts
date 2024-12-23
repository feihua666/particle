import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let feedbackReplyPrefix = '/admin/web/feedback_reply'
/**
 * 添加意见反馈回复
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(feedbackReplyPrefix + '/create',data)
}
/**
 * 删除意见反馈回复
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(feedbackReplyPrefix + '/delete',{data: data})
}
/**
 * 更新意见反馈回复
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(feedbackReplyPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(feedbackReplyPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(feedbackReplyPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(feedbackReplyPrefix + '/page',{params: data})
}

