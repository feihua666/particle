import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

import {getApiPrefix} from "../../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_DATA) + '/admin/web/dynamic_data_indicator_category'
/**
 * 添加动态数据指标分类
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/create',data)
}
/**
 * 删除动态数据指标分类
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/delete',{data: data})
}
/**
 * 更新动态数据指标分类
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(prefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/page',{params: data})
}


/**
 * 导出动态数据模板
 * @param data
 */
export const exportTemplate = (data: anyObj): AxiosPromise => {
    // 添加预期类型后，如果报错，则在全局的axiosRequest拦截中处理有一些问题，导致错误提示信息无法显示
    return axios.get(prefix + '/exportTemplate',{params: data,responseType: 'blob'})
}

/**
 * 导入数据
 * @param data
 */
export const importData = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/uploadImportData',data,{
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}
/**
 * 数据列表，分页
 * @param data 必须包含 dynamicTableId
 */
export const dataPage = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/dataPage',{params: data})
}
/**
 * 删除动态数据表格数据
 * @param data 必须包含 dynamicTableId 和 id
 */
export const dataRemove = (data: IdParam): AxiosPromise => {
    return axios.delete(prefix + '/dataDelete',{data: data})
}
