import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let prefix = '/admin/web/user-identifier-pwd'
/**
 * 添加用户登录标识密码
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 删除用户登录标识密码
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
}
/**
 * 更新用户登录标识密码
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(prefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/page',{params: data})
}

/**
 * 添加用户登录标识密码重置
 * @param data
 */
export const identifierResetPassword = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/identifier/resetPassword',data)
}
/**
 * 添加用户密码重置
 * @param data
 */
export const userResetPassword = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/user/resetPassword',data)
}

