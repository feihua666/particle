import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";

let oauth2RegisteredClientPrefix = '/front/web/oauth2_registered_client'

/**
 * 列表，获取oauth2算法
 * @param data
 */
export const algorithmList = (data: anyObj): AxiosPromise => {
    return axios.get(oauth2RegisteredClientPrefix + '/algorithm_list',{params: data})
}


