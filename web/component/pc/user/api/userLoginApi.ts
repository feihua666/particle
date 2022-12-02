import axios, { AxiosPromise} from 'axios'

/**
 * 登录
 * @param loginForm
 */
export const login = ({username,password}:{username: string,password: string}): AxiosPromise => {
    return axios.post('/login','username=' + username +'&password='+password)
}
/**
 * 退出登录
 */
export const logout = (): AxiosPromise => {
    return axios.post('/logout')
}
/**
 * 判断用户是否登录
 */
export const hasLogin = (): AxiosPromise => {
    return axios.get('/hasLogin')
}
/**
 * 获取当前登录用户的信息
 */
export const userinfo = (): AxiosPromise => {
    return axios.get('/userinfo')
}