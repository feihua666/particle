import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let schedulerTempTaskRunRecordPrefix = '/admin/web/scheduler_temp_task_run_record'

/**
 * 删除任务计划临时任务运行记录
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(schedulerTempTaskRunRecordPrefix + '/delete',{data: data})
}
/**
 * 更新任务计划临时任务运行记录
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(schedulerTempTaskRunRecordPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(schedulerTempTaskRunRecordPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(schedulerTempTaskRunRecordPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(schedulerTempTaskRunRecordPrefix + '/page',{params: data})
}

