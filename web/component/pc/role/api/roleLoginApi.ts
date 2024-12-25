import axios, {AxiosPromise} from 'axios'

/**
 * 当前登录用户的角色
 * @param data
 */
export const currentUserRoleList = (): AxiosPromise => {
    return axios.get('/role/login' + '/getList')
}
