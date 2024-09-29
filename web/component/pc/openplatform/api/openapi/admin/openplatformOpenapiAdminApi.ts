import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformOpenapiPrefix = '/admin/web/openplatform_openapi'
/**
 * 添加开放平台开放接口
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiPrefix + '/create',data)
}
/**
 * 删除开放平台开放接口
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformOpenapiPrefix + '/delete',{data: data})
}
/**
 * 更新开放平台开放接口
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformOpenapiPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformOpenapiPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiPrefix + '/list',{params: data})
}
/**
 * 根据开放平台应用id查询列表，没有分页
 * @param data
 */
export const listByOpenplatformAppId = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformOpenapiPrefix + '/listByOpenplatformAppId',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiPrefix + '/page',{params: data})
}
/**
 * 接口单次查询
 * @param data
 */
export const singleQuery = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiPrefix + '/singleQuery',data)
}
/**
 * 接口批量查询
 * @param data
 */
export const batchQuery = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformOpenapiPrefix + '/batchQuery',data,{
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}
/**
 * 下载批量查询模板
 * @param data
 */
export const downloadBatchQueryTemplate = (data: anyObj): AxiosPromise => {
    // 添加预期类型后，如果报错，则在全局的axiosRequest拦截中处理有一些问题，导致错误提示信息无法显示
    return axios.get(openplatformOpenapiPrefix + '/downloadBatchQueryTemplate',{params: data,responseType: 'blob'})
}