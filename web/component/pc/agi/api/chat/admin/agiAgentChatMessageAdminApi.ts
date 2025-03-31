import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let agiAgentChatMessagePrefix = '/admin/web/agi_agent_chat_message'
/**
 * 添加智能体对话消息
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(agiAgentChatMessagePrefix + '/create',data)
}
/**
 * 删除智能体对话消息
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(agiAgentChatMessagePrefix + '/delete',{data: data})
}
/**
 * 更新智能体对话消息
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(agiAgentChatMessagePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(agiAgentChatMessagePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(agiAgentChatMessagePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(agiAgentChatMessagePrefix + '/page',{params: data})
}

