import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam} from "../../../../../../common/api/api";

import {getApiPrefix} from "../../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_AGI) + '/front/web/agi_agent'

/**
 * 查看时使用，加载要查看的数据
 * @param data
 */
export const detail = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/detail',{params: data})
}

/**
 * 智能体对话
 * @param data
 */
export const chatStream = (data: anyObj): AxiosPromise => {
    return axios.post(prefix + '/chatStream',data,{
        adapter: ['fetch' , 'xhr' , 'http'],
        responseType: 'stream'
    })
}
