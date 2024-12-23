import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../common/api/api";

let navigationStaticDeployPrefix = '/admin/web/navigation_static_deploy'
/**
 * 添加导航网站静态部署
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(navigationStaticDeployPrefix + '/create',data)
}
/**
 * 删除导航网站静态部署
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(navigationStaticDeployPrefix + '/delete',{data: data})
}
/**
 * 更新导航网站静态部署
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(navigationStaticDeployPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(navigationStaticDeployPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(navigationStaticDeployPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(navigationStaticDeployPrefix + '/page',{params: data})
}
/**
 * 导航网站静态部署
 * @param data
 */
export const deploy = (data: anyObj): AxiosPromise => {
    return axios.post(navigationStaticDeployPrefix + '/deploy',data)
}
