import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let feedbackPrefix = '/admin/web/feedback'
/**
 * 添加意见反馈
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(feedbackPrefix + '/create',data)
}
/**
 * 删除意见反馈
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(feedbackPrefix + '/delete',{data: data})
}
/**
 * 手动处理意见反馈
 * @param data
 */
export const manualHandle = (data: updateParam): AxiosPromise => {
    return axios.post(feedbackPrefix + '/manualHandle',data)
}

/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(feedbackPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(feedbackPrefix + '/page',{params: data})
}

