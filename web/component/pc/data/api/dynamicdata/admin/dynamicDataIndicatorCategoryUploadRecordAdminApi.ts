import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let dynamicDataIndicatorCategoryUploadRecordPrefix = '/admin/web/dynamic_data_indicator_category_upload_record'
/**
 * 添加动态数据指标分类上传记录
 * @param data
 */
export const create = (data: anyObj): AxiosPromise => {
    return axios.post(dynamicDataIndicatorCategoryUploadRecordPrefix + '/create',data)
}
/**
 * 删除动态数据指标分类上传记录
 * @param data
 */
export const remove = (data: IdParam): AxiosPromise => {
    return axios.delete(dynamicDataIndicatorCategoryUploadRecordPrefix + '/delete',{data: data})
}
/**
 * 更新动态数据指标分类上传记录
 * @param data
 */
export const update = (data: updateParam): AxiosPromise => {
    return axios.put(dynamicDataIndicatorCategoryUploadRecordPrefix + '/update',data)
}
/**
 * 更新时使用，加载要更新的数据
 * @param data
 */
export const detailForUpdate = (data: IdParam): AxiosPromise => {
    return axios.get(dynamicDataIndicatorCategoryUploadRecordPrefix + '/detail-for-update',{params: data})
}
/**
 * 列表，没有分页，查询全部数据
 * @param data
 */
export const list = (data: anyObj): AxiosPromise => {
    return axios.get(dynamicDataIndicatorCategoryUploadRecordPrefix + '/list',{params: data})
}
/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(dynamicDataIndicatorCategoryUploadRecordPrefix + '/page',{params: data})
}
/**
 * 发布动态数据指标分类上传记录
 * @param data
 */
export const publish = (data: IdParam): AxiosPromise => {
    return axios.put(dynamicDataIndicatorCategoryUploadRecordPrefix + '/publish',data)
}
