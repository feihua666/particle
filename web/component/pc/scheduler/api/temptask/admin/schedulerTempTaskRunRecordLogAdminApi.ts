import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam} from "../../../../../../common/api/api";

let schedulerTempTaskRunRecordLogPrefix = '/admin/web/scheduler_temp_task_run_record_log'

/**
 * 删除任务计划临时任务运行记录日志
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(schedulerTempTaskRunRecordLogPrefix + '/delete',{data: data})
}

/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(schedulerTempTaskRunRecordLogPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(schedulerTempTaskRunRecordLogPrefix + '/page',{params: data})
}

