import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam} from "../../../../../../common/api/api";

let agiAgentPrefix = '/front/web/agi_agent'

/**
 * 查看时使用，加载要查看的数据
 * @param data
 */
export const detail = (data: IdParam): AxiosPromise => {
    return axios.get(agiAgentPrefix + '/detail',{params: data})
}

/**
 * 智能体对话
 * @param data
 */
export const chatStream = (data: anyObj): AxiosPromise => {
    return axios.post(agiAgentPrefix + '/chatStream',data,{
        adapter: ['fetch' , 'xhr' , 'http'],
        responseType: 'stream'
    })
}
