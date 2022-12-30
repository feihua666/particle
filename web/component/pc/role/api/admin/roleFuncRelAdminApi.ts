import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let prefix = '/admin/web/role-func-rel'
/**
 * 添加功能菜单角色
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 删除功能菜单角色
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
export interface RoleAssignFunc{
    roleId: string,// 角色id
    checkedFuncIds?: string[],// 选择的功能菜单id
    checkedFuncIds?: string[],// 未选择的功能菜单id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 角色分配功能菜单
 * @param data
 */
export const roleAssignFunc = (data: RoleAssignFunc): AxiosPromise => {
    return axios.post(prefix + '/role/assign/func',data)
}
/**
 * 根据角色ID查询已分配的功能菜单id
 * @param data
 */
export const queryFuncIdsByRoleId = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/queryFuncIdsByRoleId',{params: data})
}
/**
 * 清空角色下的所有功能菜单
 * @param data
 */
export const deleteByRoleId = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/deleteByRoleId',{data: data})
}
export interface FuncAssignRole{
    funcId: string,// 功能菜单id
    checkedRoleIds?: string[],// 选择的角色id
    checkedRoleIds?: string[],// 未选择的角色id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 功能菜单分配角色
 * @param data
 */
export const funcAssignRole = (data: FuncAssignRole): AxiosPromise => {
    return axios.post(prefix + '/func/assign/role',data)
}
/**
 * 根据功能菜单ID查询已分配的角色id
 * @param data
 */
export const queryRoleIdsByFuncId = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/queryRoleIdsByFuncId',{params: data})
}
/**
 * 清空功能菜单下的所有角色
 * @param data
 */
export const deleteByFuncId = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/deleteByFuncId',{data: data})
}
export default {create,remove,list,page,roleAssignFunc}