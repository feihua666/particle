import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformDocApiPrefix = '/admin/web/openplatform_doc_api'
/**
 * 添加开放接口文档接口
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(openplatformDocApiPrefix + '/create',data)
}
/**
 * 删除开放接口文档接口
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(openplatformDocApiPrefix + '/delete',{data: data})
}
/**
 * 更新开放接口文档接口
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(openplatformDocApiPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(openplatformDocApiPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocApiPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformDocApiPrefix + '/page',{params: data})
}

/**
 * 下载接口文档
 * 入参示例：{
 * "openplatformDocApiId": "1811637905995612162",
 * "convertToPdf": true
 * }
 * 返回示例：
 * {
 *     "success": true,
 *     "status": null,
 *     "errCode": null,
 *     "errMessage": null,
 *     "data": {
 *         "url": "http://localhost:8080/oss/download/report/2024-07-13/a8a1071e7dac4f77bd0d5dfbab2a3f2c--particle示例接口-20240713111313.pdf"
 *     }
 * }
 * @param data
 */
export const downloadApiDoc = (data: anyObj): AxiosPromise => {
    // 该下载接口实际调用的是报告管理接口中的接口
    return axios.post('/api/re/openplatform_doc_html',data)
}

