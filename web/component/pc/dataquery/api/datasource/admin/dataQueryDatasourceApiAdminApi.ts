import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

const dataQueryDatasourceApiPrefix = '/admin/web/data_query_datasource_api'
/**
 * 添加数据查询数据源接口
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataQueryDatasourceApiPrefix + '/create',data)
}
/**
 * 复制数据查询数据源接口
 * @param data
 */
export const copy = (data: anyObj): AxiosPromise => {
    return axios.post(dataQueryDatasourceApiPrefix + '/copy',data)
}
/**
 * 复制数据查询数据源接口
 * @param data
 */
export const copydev = (data: anyObj): AxiosPromise => {
    return axios.post(dataQueryDatasourceApiPrefix + '/copydev',data)
}
/**
 * dev提交至master
 * @param data
 */
export const devMergeToMaster = (data: anyObj): AxiosPromise => {
    return axios.put(dataQueryDatasourceApiPrefix + '/devMergeToMaster',data)
}
/**
 * 删除数据查询数据源接口
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataQueryDatasourceApiPrefix + '/delete',{data: data})
}
/**
 * 删除缓存数据查询数据源接口
 * @param data
 */
export const deleteCache = (data: IdParam): AxiosPromise => {
    return axios.delete(dataQueryDatasourceApiPrefix + '/deleteCache',{data: data})
}
/**
 * 刷新缓存数据查询数据源接口
 * @param data
 */
export const refreshCache = (data: IdParam): AxiosPromise => {
    return axios.put(dataQueryDatasourceApiPrefix + '/refreshCache',data)
}
/**
 * 更新数据查询数据源接口
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataQueryDatasourceApiPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataQueryDatasourceApiPrefix + '/detail-for-update',{params: data})
}

/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataQueryDatasourceApiPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataQueryDatasourceApiPrefix + '/page',{params: data})
}

/**
 * 测试数据查询数据源接口
 * @param data
 */
const dataQueryDatasourceApiTestPrefix = dataQueryDatasourceApiPrefix + '/test'


export interface ApiTestParam{
    // 接口id
    dataQueryDatasourceApiId: string,
    // 数据
    param?: any,
    queryString?: string
}
export const apiTest = (data: ApiTestParam): AxiosPromise => {
    return axios.post(dataQueryDatasourceApiTestPrefix + '/api_test',data,{timeout: 15 * 60 * 1000})
}
/**
 * 获取详情数据，目前添加该方法主要是测试接口时加载用例数据
 * @param data
 */
export const detail = (data: IdParam): AxiosPromise => {
    return axios.get(dataQueryDatasourceApiPrefix + '/detail',{params: data})
}
