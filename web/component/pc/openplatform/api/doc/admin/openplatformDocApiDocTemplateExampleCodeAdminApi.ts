import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformDocApiDocTemplateExampleCodePrefix = '/admin/web/openplatform_doc_api_doc_template_example_code'
/**
 * 添加开放接口文档模板示例代码
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformDocApiDocTemplateExampleCodePrefix + '/create',data)
}
/**
 * 删除开放接口文档模板示例代码
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformDocApiDocTemplateExampleCodePrefix + '/delete',{data: data})
}
/**
 * 更新开放接口文档模板示例代码
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformDocApiDocTemplateExampleCodePrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformDocApiDocTemplateExampleCodePrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocApiDocTemplateExampleCodePrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocApiDocTemplateExampleCodePrefix + '/page',{params: data})
}

