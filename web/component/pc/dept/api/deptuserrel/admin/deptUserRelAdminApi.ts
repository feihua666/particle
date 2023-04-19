import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let deptUserRelPrefix = '/admin/web/dept_user_rel'
/**
 * 添加部门用户关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(deptUserRelPrefix + '/create',data)
}
/**
 * 删除部门用户关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(deptUserRelPrefix + '/delete',{data: data})
}
/**
 * 更新部门用户关系
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(deptUserRelPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(deptUserRelPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(deptUserRelPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(deptUserRelPrefix + '/page',{params: data})
}

