import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import getApiPrefix from "../../../../../../common/api/apiPrefixConfig";

let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_OAUTH2_AUTHORIZATION) + '/front/web/oauth2_registered_client'

/**
 * 列表，获取oauth2算法
 * @param data
 */
export const algorithmList = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/algorithm_list',{params: data})
}


