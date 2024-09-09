import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let schedulerExecuteRecordPrefix = '/admin/web/scheduler_execute_record'
/**
 * 添加任务计划执行记录
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(schedulerExecuteRecordPrefix + '/create',data)
}
/**
 * 删除任务计划执行记录
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(schedulerExecuteRecordPrefix + '/delete',{data: data})
}
/**
 * 更新任务计划执行记录
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(schedulerExecuteRecordPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(schedulerExecuteRecordPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(schedulerExecuteRecordPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(schedulerExecuteRecordPrefix + '/page',{params: data})
}

