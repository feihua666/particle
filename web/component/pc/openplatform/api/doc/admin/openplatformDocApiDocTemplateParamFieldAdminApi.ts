import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformDocApiDocTemplateParamFieldPrefix = '/admin/web/openplatform_doc_api_doc_template_param_field'
/**
 * 添加开放接口文档模板参数字段
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformDocApiDocTemplateParamFieldPrefix + '/create',data)
}
/**
 * 解析并添加开放接口文档模板参数字段
 * @param data
 */
export const parseAndCreate = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformDocApiDocTemplateParamFieldPrefix + '/parse-and-create',data)
}
/**
 * 删除开放接口文档模板参数字段
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformDocApiDocTemplateParamFieldPrefix + '/delete',{data: data})
}
/**
 * 更新开放接口文档模板参数字段
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformDocApiDocTemplateParamFieldPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformDocApiDocTemplateParamFieldPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocApiDocTemplateParamFieldPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocApiDocTemplateParamFieldPrefix + '/page',{params: data})
}

