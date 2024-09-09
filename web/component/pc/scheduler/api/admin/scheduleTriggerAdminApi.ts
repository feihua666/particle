import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools"

let scheduleTriggerPrefix = '/admin/web/schedule/trigger'
/**
 * 暂停触发器
 * @param data
 */
export const pauseTrigger = (data: anyObj): AxiosPromise => {
    return axios.post(scheduleTriggerPrefix + '/pauseTrigger',data)
}
/**
 * 恢复触发器
 * @param data
 */
export const resumeTrigger = (data: anyObj): AxiosPromise => {
    return axios.post(scheduleTriggerPrefix + '/resumeTrigger',data)
}
/**
 * 触发器计划列表，没有分页，查询全部数据
 * @param data
 */
export const getTriggerList = (data: anyObj): AxiosPromise => {
    return axios.get(scheduleTriggerPrefix + '/getTriggerList',{params: data})
}


