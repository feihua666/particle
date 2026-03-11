import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

import {getApiPrefix} from "../../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_AGI) + '/front/web/agi_agent_chat_message'

/**
 * 列表，分页
 * @param data
 */
export const page = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/page',{params: data})
}

