import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../../../global/common/tools/ObjectTools";
import {IdParam, updateParam} from "../../../../../../common/api/api";

let openplatformAppPrefix = '/front/web/openplatform_app'

/**
 * 列表，获取开放平台算法
 * @param data
 */
export const algorithmList = (data: anyObj): AxiosPromise => {
    return axios.get(openplatformAppPrefix + '/algorithm_list',{params: data})
}

