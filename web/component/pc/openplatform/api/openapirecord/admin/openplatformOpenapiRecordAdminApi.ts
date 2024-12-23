import axios, {AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam} from "../../../../../../common/api/api";

let openplatformOpenapiRecordPrefix = '/admin/web/openplatform_openapi_record'
let openplatformOpenapiRecordParamPrefix = '/admin/web/openplatform_openapi_record_param'

/**
 * 删除开放平台开放接口调用记录
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformOpenapiRecordPrefix + '/delete',{data: data})
}

/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordPrefix + '/page',{params: data})
}

/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const paramDetailByOpenplatformOpenapiRecordId = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformOpenapiRecordParamPrefix + '/detailByOpenplatformOpenapiRecordId',{params: data})
}
