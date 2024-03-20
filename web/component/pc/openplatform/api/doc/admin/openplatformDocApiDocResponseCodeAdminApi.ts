import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformDocApiDocResponseCodePrefix = '/admin/web/openplatform_doc_api_doc_response_code'
/**
 * 添加开放接口文档响应码
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformDocApiDocResponseCodePrefix + '/create',data)
}
/**
 * 删除开放接口文档响应码
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformDocApiDocResponseCodePrefix + '/delete',{data: data})
}
/**
 * 更新开放接口文档响应码
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformDocApiDocResponseCodePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformDocApiDocResponseCodePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocApiDocResponseCodePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocApiDocResponseCodePrefix + '/page',{params: data})
}

