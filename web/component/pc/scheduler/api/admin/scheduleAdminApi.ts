import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../global/common/tools/ObjectTools"
import getApiPrefix from "../../../../../common/api/apiPrefixConfig";

let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_SCHEDULER) + '/admin/web/schedule'
/**
 * 挂起任务计划
 * @param data
 */
export const standby = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/standby',data)
}
/**
 * 启动任务计划
 * @param data
 */
export const start = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/start',data)
}
/**
 * 停止任务计划
 * @param data
 */
export const shutdown = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/shutdown',data)
}
/**
 * 任务计划列表，没有分页，查询全部数据
 * @param data
 */
export const getScheduleList = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/getScheduleList',{params: data})
}
