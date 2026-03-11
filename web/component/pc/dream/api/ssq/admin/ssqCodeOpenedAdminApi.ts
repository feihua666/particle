import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import getApiPrefix from "../../../../../../common/api/apiPrefixConfig";

let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_DREAM) + '/admin/web/ssq_code_opened'
/**
 * 初始化所有双色球开奖号码
 * @param data
 */
export const allCodeInit = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/allCodeInit',data,{timeout: 10 * 60 * 1000})
}
/**
 * 更新所有双色球开奖号码
 * @param data
 */
export const allCodeUpdate = (data: anyObj): AxiosPromise => {
    return axios.put(prefix + '/allCodeUpdate',data)
}
/**
 * 停止初始化所有双色球开奖号码
 * @param data
 */
export const allCodeStop = (data: anyObj): AxiosPromise => {
    return axios.put(prefix + '/allCodeStop',data)
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/list',{params: data,timeout: 10 * 60 * 1000})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/page',{params: data,timeout: 10 * 60 * 1000})
}
/**
 * 预测调参
 * @param data
 */
export const predictionParameterTuning = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/predictionParameterTuning',data,{timeout: 10 * 60 * 1000})
}

