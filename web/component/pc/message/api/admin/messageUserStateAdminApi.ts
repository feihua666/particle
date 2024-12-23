import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let messageUserStatePrefix = '/admin/web/message_user_state'
/**
 * 添加用户消息读取状态
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(messageUserStatePrefix + '/create',data)
}
/**
 * 删除用户消息读取状态
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(messageUserStatePrefix + '/delete',{data: data})
}
/**
 * 更新用户消息读取状态
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(messageUserStatePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(messageUserStatePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(messageUserStatePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(messageUserStatePrefix + '/page',{params: data})
}

