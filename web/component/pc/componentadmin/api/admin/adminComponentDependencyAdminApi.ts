import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let adminComponentDependencyPrefix = '/admin/web/admin_component_dependency'
/**
 * 添加组件依赖关系
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(adminComponentDependencyPrefix + '/create',data)
}
/**
 * 删除组件依赖关系
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(adminComponentDependencyPrefix + '/delete',{data: data})
}
/**
 * 更新组件依赖关系
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(adminComponentDependencyPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(adminComponentDependencyPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(adminComponentDependencyPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(adminComponentDependencyPrefix + '/page',{params: data})
}

export interface ComponentAssignDependComponent{
    componentId: string,// 源组件id
    checkedDependComponentIds?: string[],// 选择的依赖组件id
    uncheckedDependComponentIds?: string[],// 未选择的依赖组件id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 源组件分配依赖组件
 * @param data
 */
export const componentAssignDependComponent = (data: ComponentAssignDependComponent): AxiosPromise => {
    return axios.post(adminComponentDependencyPrefix + '/component/assign/dependComponent',data)
}
/**
 * 根据源组件ID查询已分配的依赖组件id
 * @param data
 */
export const queryDependComponentIdsByComponentId = (data: IdParam): AxiosPromise => {
    return axios.get(adminComponentDependencyPrefix + '/queryDependComponentIdsByComponentId',{params: data})
}
/**
 * 清空源组件下的所有依赖组件
 * @param data
 */
export const deleteByComponentId = (data: IdParam): AxiosPromise => {
    return axios.delete(adminComponentDependencyPrefix + '/deleteByComponentId',{data: data})
}
export interface DependComponentAssignComponent{
    dependComponentId: string,// 依赖组件id
    checkedComponentIds?: string[],// 选择的源组件id
    uncheckedComponentIds?: string[],// 未选择的源组件id,如果为懒加载请传该值
    isLazyLoad: boolean // 页面可选择的数据是否为懒加载
}
/**
 * 依赖组件分配源组件
 * @param data
 */
export const dependComponentAssignComponent = (data: DependComponentAssignComponent): AxiosPromise => {
    return axios.post(adminComponentDependencyPrefix + '/dependComponent/assign/component',data)
}
/**
 * 根据依赖组件ID查询已分配的源组件id
 * @param data
 */
export const queryComponentIdsByDependComponentId = (data: IdParam): AxiosPromise => {
    return axios.get(adminComponentDependencyPrefix + '/queryComponentIdsByDependComponentId',{params: data})
}
/**
 * 清空依赖组件下的所有源组件
 * @param data
 */
export const deleteByDependComponentId = (data: IdParam): AxiosPromise => {
    return axios.delete(adminComponentDependencyPrefix + '/deleteByDependComponentId',{data: data})
}
