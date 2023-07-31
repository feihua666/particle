import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let oauth2RegisteredClientPrefix = '/admin/web/oauth2_registered_client'
/**
 * 添加oauth2客户端
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(oauth2RegisteredClientPrefix + '/create',data)
}
/**
 * 删除oauth2客户端
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(oauth2RegisteredClientPrefix + '/delete',{data: data})
}
/**
 * 更新oauth2客户端
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(oauth2RegisteredClientPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(oauth2RegisteredClientPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(oauth2RegisteredClientPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(oauth2RegisteredClientPrefix + '/page',{params: data})
}

