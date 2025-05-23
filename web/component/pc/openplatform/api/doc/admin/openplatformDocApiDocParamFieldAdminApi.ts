import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformDocApiDocParamFieldPrefix = '/admin/web/openplatform_doc_api_doc_param_field'
/**
 * 添加开放接口文档参数字段
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformDocApiDocParamFieldPrefix + '/create',data)
}
/**
 * 解析并添加开放接口文档参数字段
 * @param data
 */
export const parseAndCreate = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformDocApiDocParamFieldPrefix + '/parse-and-create',data)
}
/**
 * 删除开放接口文档参数字段
 * @param data
 */
export const conditionRemove = (data: anyObj): AxiosPromise => {
    return axios.delete(openplatformDocApiDocParamFieldPrefix + '/conditionDelete',data)
}
/**
 * 删除开放接口文档参数字段
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformDocApiDocParamFieldPrefix + '/delete',{data: data})
}
/**
 * 更新开放接口文档参数字段
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformDocApiDocParamFieldPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformDocApiDocParamFieldPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocApiDocParamFieldPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocApiDocParamFieldPrefix + '/page',{params: data})
}

