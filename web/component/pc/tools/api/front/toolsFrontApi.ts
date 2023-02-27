import axios, { AxiosPromise} from 'axios'
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
    return axios.get('front/web/cron' + '/cronRunTimes',{params: data})
}