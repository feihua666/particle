import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../global/common/tools/ObjectTools"
import getApiPrefix from "../../../../../common/api/apiPrefixConfig";

let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_SCHEDULER) + '/admin/web/schedule/job'
/**
 * 添加任务
 * @param data
 */
export const addJob = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/addJob',data)
}
/**
 * 复制任务
 * @param data
 */
export const copyJob = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/copyJob',data)
}
/**
 * 更新任务
 * @param data
 */
export const updateJob = (data: anyObj): AxiosPromise => {
    return axios.put(prefix + '/updateJob',data)
}
/**
 * 删除任务
 * @param data
 */
export const deleteJob = (data: anyObj): AxiosPromise => {
    return axios.delete(prefix + '/deleteJob',data)
}
/**
 * 暂停任务
 * @param data
 */
export const pauseJob = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/pauseJob',data)
}
/**
 * 恢复任务
 * @param data
 */
export const resumeJob = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/resumeJob',data)
}
/**
 * 手动执行一次任务
 * @param data
 */
export const executeOnce = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/executeOnce',data)
}
/**
 * 获取任务
 * @param data
 */
export const getJobDetailExt = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/getJobDetailExt',{params: data})
}
/**
 * 任务计划列表，没有分页，查询全部数据
 * @param data
 */
export const getJobDetailList = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/getJobDetailList',{params: data})
}


