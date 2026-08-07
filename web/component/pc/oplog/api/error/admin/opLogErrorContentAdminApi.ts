import axios, {type AxiosPromise} from 'axios'
import {IdParam} from "../../../../../../common/api/api";

import {getApiPrefix} from "../../../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_OP_LOG) + '/admin/web/op_log_error_content'
/**
 * 根据异常日志id查看异常日志内容
 * @param data
 */
export const detailByOpLogErrorId = (data: IdParam): AxiosPromise => {
    return axios.get(prefix + '/detailByOpLogErrorId',{params: data})
}


