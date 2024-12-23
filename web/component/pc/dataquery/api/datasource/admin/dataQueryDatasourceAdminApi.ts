import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dataQueryDatasourcePrefix = '/admin/web/data_query_datasource'
/**
 * 添加数据查询数据源
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dataQueryDatasourcePrefix + '/create',data)
}
/**
 * 删除数据查询数据源
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dataQueryDatasourcePrefix + '/delete',{data: data})
}
/**
 * 更新数据查询数据源
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dataQueryDatasourcePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dataQueryDatasourcePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dataQueryDatasourcePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dataQueryDatasourcePrefix + '/page',{params: data})
}

