import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformDocDirNamePrefix = '/admin/web/openplatform_doc_dir_name'
/**
 * 添加开放接口目录名称
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformDocDirNamePrefix + '/create',data)
}
/**
 * 删除开放接口目录名称
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformDocDirNamePrefix + '/delete',{data: data})
}
/**
 * 更新开放接口目录名称
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformDocDirNamePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformDocDirNamePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocDirNamePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocDirNamePrefix + '/page',{params: data})
}

