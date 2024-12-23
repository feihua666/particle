import axios, {AxiosPromise} from 'axios'
import {IdParam} from "../../../../../../common/api/api";

let opLogErrorContentPrefix = '/admin/web/op_log_error_content'
/**
 * 根据异常日志id查看异常日志内容
 * @param data
 */
export const detailByOpLogErrorId = (data: IdParam): AxiosPromise => {
    return axios.get(opLogErrorContentPrefix + '/detailByOpLogErrorId',{params: data})
}


