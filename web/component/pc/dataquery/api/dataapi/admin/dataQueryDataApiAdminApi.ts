import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataQueryDataApiPrefix = '/admin/web/data_query_data_api'
/**
 * 添加数据查询数据接口
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataQueryDataApiPrefix + '/create',data)
}
/**
 * 删除数据查询数据接口
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataQueryDataApiPrefix + '/delete',{data: data})
}
/**
 * 更新数据查询数据接口
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataQueryDataApiPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataQueryDataApiPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataQueryDataApiPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataQueryDataApiPrefix + '/page',{params: data})
}
/**
 * 测试数据查询数据源接口
 * @param data
 */
const dataQueryDataApiTestPrefix = dataQueryDataApiPrefix + '/test'


export interface ApiTestParam{
    // 接口地址
    url: string,
    // 数据
    param: any
}
export const apiTest = (data: ApiTestParam): AxiosPromise => {
    return axios.post(dataQueryDataApiTestPrefix + '/api_test',data)
}

/**
 * 获取详情数据，目前添加该方法主要是测试接口时加载用例数据
 * @param data
 */
export const detail = (data: IdParam): AxiosPromise => {
    return axios.get(dataQueryDataApiPrefix + '/detail',{params: data})
}