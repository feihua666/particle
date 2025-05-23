import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let schedulerJobDataTaskPrefix = '/admin/web/scheduler_job_data_task'
/**
 * 添加任务计划任务数据
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(schedulerJobDataTaskPrefix + '/create',data)
}
/**
 * 删除任务计划任务数据
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(schedulerJobDataTaskPrefix + '/delete',{data: data})
}
/**
 * 更新任务计划任务数据
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(schedulerJobDataTaskPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(schedulerJobDataTaskPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(schedulerJobDataTaskPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(schedulerJobDataTaskPrefix + '/page',{params: data})
}

