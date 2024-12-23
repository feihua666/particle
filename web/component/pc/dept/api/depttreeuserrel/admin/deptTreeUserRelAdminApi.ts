import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let deptTreeUserRelPrefix = '/admin/web/dept_tree_user_rel'
/**
 * 添加部门树用户关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(deptTreeUserRelPrefix + '/create',data)
}
/**
 * 删除部门树用户关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(deptTreeUserRelPrefix + '/delete',{data: data})
}
/**
 * 更新部门树用户关系
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(deptTreeUserRelPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(deptTreeUserRelPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(deptTreeUserRelPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(deptTreeUserRelPrefix + '/page',{params: data})
}

