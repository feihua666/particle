import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../global/common/tools/ObjectTools";
import {IdParam} from "../../../../common/api/api";

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


/**
 * 切换租户
 * @param data
 */
export const changeTenant = (data: IdParam): AxiosPromise => {
    return axios.post('/changeTenant',data)
}
/**
 * 切换角色
 * @param data
 */
export const changeRole = (data: IdParam): AxiosPromise => {
    return axios.post('/changeRole',data)
}