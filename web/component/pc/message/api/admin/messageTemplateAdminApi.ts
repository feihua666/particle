import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let messageTemplatePrefix = '/admin/web/message_template'
/**
 * 添加消息模板
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(messageTemplatePrefix + '/create',data)
}
/**
 * 删除消息模板
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(messageTemplatePrefix + '/delete',{data: data})
}
/**
 * 更新消息模板
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(messageTemplatePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(messageTemplatePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(messageTemplatePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(messageTemplatePrefix + '/page',{params: data})
}

