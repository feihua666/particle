import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools"

let schedulePrefix = '/admin/web/schedule'
/**
 * 挂起任务计划
 * @param data
 */
export const standby = (data: anyObj): AxiosPromise => {
    return axios.post(schedulePrefix + '/standby',data)
}
/**
 * 启动任务计划
 * @param data
 */
export const start = (data: anyObj): AxiosPromise => {
    return axios.post(schedulePrefix + '/start',data)
}
/**
 * 停止任务计划
 * @param data
 */
export const shutdown = (data: anyObj): AxiosPromise => {
    return axios.post(schedulePrefix + '/shutdown',data)
}
/**
 * 任务计划列表，没有分页，查询全部数据
 * @param data
 */
export const getScheduleList = (data: anyObj): AxiosPromise => {
    return axios.get(schedulePrefix + '/getScheduleList',{params: data})
}
