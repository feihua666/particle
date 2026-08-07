import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import getApiPrefix from "../../../../../../common/api/apiPrefixConfig";

let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_OPEN_PLATFORM) + '/front/web/openplatform_app'

/**
 * 列表，获取开放平台算法
 * @param data
 */
export const algorithmList = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/algorithm_list',{params: data})
}

