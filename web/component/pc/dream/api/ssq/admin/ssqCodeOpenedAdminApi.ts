import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let ssqCodeOpenedPrefix = '/admin/web/ssq_code_opened'
/**
 * 添加双色球开奖
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(ssqCodeOpenedPrefix + '/create',data)
}
/**
 * 删除双色球开奖
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(ssqCodeOpenedPrefix + '/delete',{data: data})
}
/**
 * 更新双色球开奖
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(ssqCodeOpenedPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(ssqCodeOpenedPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(ssqCodeOpenedPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(ssqCodeOpenedPrefix + '/page',{params: data})
}

