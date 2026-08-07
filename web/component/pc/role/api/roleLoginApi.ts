import axios, {type AxiosPromise} from 'axios'
import {getApiPrefix} from "../../../../common/api/apiPrefixConfig";
let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_ROLE)
/**
 * 当前登录用户的角色
 * @param data
 */
export const currentUserRoleList = (): AxiosPromise => {
    return axios.get(prefix + '/role/login' + '/getList')
}
