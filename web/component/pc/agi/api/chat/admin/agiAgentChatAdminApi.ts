import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let agiAgentChatPrefix = '/admin/web/agi_agent_chat'
/**
 * 添加智能体对话
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(agiAgentChatPrefix + '/create',data)
}
/**
 * 删除智能体对话
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(agiAgentChatPrefix + '/delete',{data: data})
}
/**
 * 更新智能体对话
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(agiAgentChatPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(agiAgentChatPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(agiAgentChatPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(agiAgentChatPrefix + '/page',{params: data})
}

