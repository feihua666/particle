import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let prefix = '/admin/web/role-user-rel'
/**
 * 添加用户角色
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 删除用户角色
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
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
export interface RoleAssignUser{
    roleId: string,// 角色id
    checkedUserIds?: string[],// 选择的用户id
    uncheckedUserIds?: string[],// 未选择的用户id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 角色分配用户
 * @param data
 */
export const roleAssignUser = (data: RoleAssignUser): AxiosPromise => {
    return axios.post(prefix + '/role/assign/user',data)
}
/**
 * 根据角色ID查询已分配的用户id
 * @param data
 */
export const queryUserIdsByRoleId = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/queryUserIdsByRoleId',{params: data})
}
/**
 * 清空角色下的所有用户
 * @param data
 */
export const deleteByRoleId = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/deleteByRoleId',{data: data})
}
export interface UserAssignRole{
    userId: string,// 用户id
    checkedRoleIds?: string[],// 选择的角色id
    uncheckedRoleIds?: string[],// 未选择的角色id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 用户分配角色
 * @param data
 */
export const userAssignRole = (data: UserAssignRole): AxiosPromise => {
    return axios.post(prefix + '/user/assign/role',data)
}
/**
 * 根据用户ID查询已分配的角色id
 * @param data
 */
export const queryRoleIdsByUserId = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/queryRoleIdsByUserId',{params: data})
}
/**
 * 清空用户下的所有角色
 * @param data
 */
export const deleteByUserId = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/deleteByUserId',{data: data})
}
