import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let deptTreePrefix = '/admin/web/dept_tree'
/**
 * 添加部门树
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(deptTreePrefix + '/create',data)
}
/**
 * 删除部门树
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(deptTreePrefix + '/delete',{data: data})
}
/**
 * 更新部门树
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(deptTreePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(deptTreePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(deptTreePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(deptTreePrefix + '/page',{params: data})
}

