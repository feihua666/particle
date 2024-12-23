import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let roleDataScopeRelPrefix = '/admin/web/role_data_scope_rel'
/**
 * 添加角色数据范围关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(roleDataScopeRelPrefix + '/create',data)
}
/**
 * 删除角色数据范围关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(roleDataScopeRelPrefix + '/delete',{data: data})
}
/**
 * 更新角色数据范围关系
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(roleDataScopeRelPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(roleDataScopeRelPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(roleDataScopeRelPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(roleDataScopeRelPrefix + '/page',{params: data})
}

export interface RoleAssignDataScope{
    roleId: string,// 角色id
    checkedDataScopeIds?: string[],// 选择的数据范围id
    uncheckedDataScopeIds?: string[],// 未选择的数据范围id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 角色分配数据范围
 * @param data
 */
export const roleAssignDataScope = (data: RoleAssignDataScope): AxiosPromise => {
    return axios.post(roleDataScopeRelPrefix + '/role/assign/dataScope',data)
}
/**
 * 根据角色ID查询已分配的数据范围id
 * @param data
 */
export const queryDataScopeIdsByRoleId = (data: IdParam): AxiosPromise => {
    return axios.get(roleDataScopeRelPrefix + '/queryDataScopeIdsByRoleId',{params: data})
}
/**
 * 清空角色下的所有数据范围
 * @param data
 */
export const deleteByRoleId = (data: IdParam): AxiosPromise => {
    return axios.delete(roleDataScopeRelPrefix + '/deleteByRoleId',{data: data})
}
export interface DataScopeAssignRole{
    dataScopeId: string,// 数据范围id
    checkedRoleIds?: string[],// 选择的角色id
    uncheckedRoleIds?: string[],// 未选择的角色id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 数据范围分配角色
 * @param data
 */
export const dataScopeAssignRole = (data: DataScopeAssignRole): AxiosPromise => {
    return axios.post(roleDataScopeRelPrefix + '/dataScope/assign/role',data)
}
/**
 * 根据数据范围ID查询已分配的角色id
 * @param data
 */
export const queryRoleIdsByDataScopeId = (data: IdParam): AxiosPromise => {
    return axios.get(roleDataScopeRelPrefix + '/queryRoleIdsByDataScopeId',{params: data})
}
/**
 * 清空数据范围下的所有角色
 * @param data
 */
export const deleteByDataScopeId = (data: IdParam): AxiosPromise => {
    return axios.delete(roleDataScopeRelPrefix + '/deleteByDataScopeId',{data: data})
}
