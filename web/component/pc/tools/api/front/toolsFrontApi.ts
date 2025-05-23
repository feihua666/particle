import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";

let dictPrefix = '/front/web/dict'
export interface CronQueryParam{
    // 开始时间，不填写默认按服务器当前时间
    startAt?: string
    // cron表达式
    cronExpression: string
    // 执行次数 后端限制 [5,100]
    times: number
}
/**
 * 根据字典组编码查询字典项
 * @param data
 */
export const cronRunTimes = (data: CronQueryParam): AxiosPromise => {
    return axios.get('/front/web/cron' + '/cronRunTimes',{params: data})
}
/**
 * 添加字段
 * @param data
 */
export const addField = (data: anyObj): AxiosPromise => {
    return axios.post('/front/web/particle' + '/addField',data)
}
/**
 * 删除模型服务
 * @param data
 */
export const deleteModelService = (data: anyObj): AxiosPromise => {
    return axios.post('/front/web/particle' + '/deleteModelService',data)
}
/**
 * 批量生成id
 * @param data
 */
export const batchGenIds = (data: anyObj): AxiosPromise => {
    return axios.post('/front/web/particle' + '/batchGenIds',data)
}

/**
 * jsonparse
 * @param data
 * @return json 对象，即：res.data.data=json对象
 */
export const jsonParse = (data: anyObj): AxiosPromise => {
    return axios.post('/front/web/json' + '/parse',data)
}

/**
 * jsonparse
 * @param data={jsonStrs:[]}
 * @return json 对象，即：res.data.data=数组，数组中是字符串
 */
export const parseMultipleLine = (data: anyObj): AxiosPromise => {
    return axios.post('/front/web/json' + '/parseMultipleLine',data)
}
