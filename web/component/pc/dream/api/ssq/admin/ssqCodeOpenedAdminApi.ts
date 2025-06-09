import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";

let ssqCodeOpenedPrefix = '/admin/web/ssq_code_opened'
/**
 * 初始化所有双色球开奖号码
 * @param data
 */
export const allCodeInit = (data: anyObj): AxiosPromise => {
    return axios.post(ssqCodeOpenedPrefix + '/allCodeInit',data,{timeout: 10 * 60 * 1000})
}
/**
 * 更新所有双色球开奖号码
 * @param data
 */
export const allCodeUpdate = (data: anyObj): AxiosPromise => {
    return axios.put(ssqCodeOpenedPrefix + '/allCodeUpdate',data)
}
/**
 * 停止初始化所有双色球开奖号码
 * @param data
 */
export const allCodeStop = (data: anyObj): AxiosPromise => {
    return axios.put(ssqCodeOpenedPrefix + '/allCodeStop',data)
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(ssqCodeOpenedPrefix + '/list',{params: data,timeout: 10 * 60 * 1000})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(ssqCodeOpenedPrefix + '/page',{params: data,timeout: 10 * 60 * 1000})
}
/**
 * 预测调参
 * @param data
 */
export const predictionParameterTuning = (data: anyObj): AxiosPromise => {
    return axios.post(ssqCodeOpenedPrefix + '/predictionParameterTuning',data,{timeout: 10 * 60 * 1000})
}

