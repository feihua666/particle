import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let ssqCodePrefix = '/admin/web/ssq_code'

/**
 * 初始化所有双色球号码
 * @param data
 */
export const allCodeInit = (data: anyObj): AxiosPromise => {
    return axios.post(ssqCodePrefix + '/allCodeInit',data)
}
/**
 * 更新所有双色球号码
 * @param data
 */
export const allCodeUpdate = (data: anyObj): AxiosPromise => {
    return axios.put(ssqCodePrefix + '/allCodeUpdate',data)
}
/**
 * 停止初始化或更新所有双色球号码
 * @param data
 */
export const allCodeStop = (data: anyObj): AxiosPromise => {
    return axios.put(ssqCodePrefix + '/allCodeStop',data)
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(ssqCodePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(ssqCodePrefix + '/page',{params: data})
}

