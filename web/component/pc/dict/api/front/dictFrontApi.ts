import axios, { AxiosPromise} from 'axios'
let dictPrefix = '/front/web/dict'
export interface DictItemsParam{
    // 字典组编码
    groupCode: string
    // 私有标识
    privateFlag?: string
    // 分组标识
    groupFlag?: string
    // tags
    tags?: string
}
/**
 * 根据字典组编码查询字典项
 * @param data
 */
export const getItems = (data: DictItemsParam): AxiosPromise => {
    return axios.get(dictPrefix + '/items',{params: data})
}
/**
 * 根据字典组编码查询分组字典项
 * @param data
 */
export const getGroupItems = (data: DictItemsParam): AxiosPromise => {
    return axios.get(dictPrefix + '/groupItems',{params: data})
}
/**
 * 根据字典组编码查询字典组
 * @param data
 */
export const getGroups = (data: DictItemsParam): AxiosPromise => {
    return axios.get(dictPrefix + '/groups',{params: data})
}

export default {getItems,getGroupItems,getGroups}