import axios, { AxiosPromise} from 'axios'

/**
 * 当前登录用户的功能
 */
export const loginGetList = (): AxiosPromise => {
    return axios.get('/func/login/getList')
}