import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let funcApplicationFuncRelPrefix = '/admin/web/func_application_func_rel'
/**
 * 添加功能应用功能关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(funcApplicationFuncRelPrefix + '/create',data)
}
/**
 * 删除功能应用功能关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(funcApplicationFuncRelPrefix + '/delete',{data: data})
}
/**
 * 更新功能应用功能关系
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(funcApplicationFuncRelPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(funcApplicationFuncRelPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(funcApplicationFuncRelPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(funcApplicationFuncRelPrefix + '/page',{params: data})
}


export interface FuncApplicationAssignFunc{
    funcApplicationId: string,// 功能应用id
    checkedFuncIds?: string[],// 选择的功能id
    uncheckedFuncIds?: string[],// 未选择的功能id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 功能应用分配功能
 * @param data
 */
export const funcApplicationAssignFunc = (data: FuncApplicationAssignFunc): AxiosPromise => {
    return axios.post(funcApplicationFuncRelPrefix + '/funcApplication/assign/func',data)
}
/**
 * 根据功能应用ID查询已分配的功能id
 * @param data
 */
export const queryFuncIdsByFuncApplicationId = (data: IdParam): AxiosPromise => {
    return axios.get(funcApplicationFuncRelPrefix + '/queryFuncIdsByFuncApplicationId',{params: data})
}
/**
 * 清空功能应用下的所有功能
 * @param data
 */
export const deleteByFuncApplicationId = (data: IdParam): AxiosPromise => {
    return axios.delete(funcApplicationFuncRelPrefix + '/deleteByFuncApplicationId',{data: data})
}
export interface FuncAssignFuncApplication{
    funcId: string,// 功能id
    checkedFuncApplicationIds?: string[],// 选择的功能应用id
    uncheckedFuncApplicationIds?: string[],// 未选择的功能应用id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 功能分配功能应用
 * @param data
 */
export const funcAssignFuncApplication = (data: FuncAssignFuncApplication): AxiosPromise => {
    return axios.post(funcApplicationFuncRelPrefix + '/func/assign/funcApplication',data)
}
/**
 * 根据功能ID查询已分配的功能应用id
 * @param data
 */
export const queryFuncApplicationIdsByFuncId = (data: IdParam): AxiosPromise => {
    return axios.get(funcApplicationFuncRelPrefix + '/queryFuncApplicationIdsByFuncId',{params: data})
}
/**
 * 清空功能下的所有功能应用
 * @param data
 */
export const deleteByFuncId = (data: IdParam): AxiosPromise => {
    return axios.delete(funcApplicationFuncRelPrefix + '/deleteByFuncId',{data: data})
}
