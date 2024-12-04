import axios, { AxiosPromise} from 'axios'
import {anyObj} from "../../../../global/common/tools/ObjectTools";
import {IdParam} from "../../../../common/api/api";

/**
 * 登录
 * @param loginForm
 */
export const login = ({username,password,captchaUniqueIdentifier,captchaValue}:{username: string,password: string,captchaUniqueIdentifier: string,captchaValue: string}): AxiosPromise => {
    return axios.post('/login', {username,password,captchaUniqueIdentifier,captchaValue},{
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    })
}
/**
 * 动态验证码登录
 * @param loginForm
 */
export const loginDynamicCaptcha = ({username,password,captchaUniqueIdentifier,captchaValue}:{username: string,password: string,captchaUniqueIdentifier: string,captchaValue: string}): AxiosPromise => {
    return axios.post('/loginCaptcha', {username,password,captchaUniqueIdentifier,captchaValue},{
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    })
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
/**
 * 获取登录记录
 */
export const getLoginRecord = (): AxiosPromise => {
    return axios.get('/loginRecord')
}
/**
 * 获取登录设备
 */
export const getLoginDevice = (): AxiosPromise => {
    return axios.get('/loginDevice')
}
/**
 * 获取登录标识/我的账号
 */
export const getIdentifier = (): AxiosPromise => {
    return axios.get('/user-identifier/login/identifier')
}
/**
 * 获取登录标识/我的账号
 */
export const getIdentifierPwd = (): AxiosPromise => {
    return axios.get('/user-identifier-pwd/login/identifier-pwd')
}

export interface IdentifierPwdUpdateData{
    oldPassword: string // 原密码
    userIdentifierId: string // 登录标识
    password: string // 新密码
}
/**
 * 修改登录标识密码
 */
export const identifierPwdUpdate = (data: IdentifierPwdUpdateData): AxiosPromise => {
    return axios.post('/user-identifier-pwd/login/identifier-pwd-update',data)
}
/**
 * 获取登录验证码
 */
export const getLoginCaptcha = (): AxiosPromise => {
    return axios.get('/captcha/getCaptcha',{params: {captchaScene: '/login'}})
}
/**
 * 获取登录动态验证码
 */
export const getLoginDynamicCaptcha = (): AxiosPromise => {
    return axios.get('/captcha/getDynamicCaptcha',{params: {captchaScene: '/login'}})
}
/**
 * 获取当前登录用户修改密码验证码
 */
export const getLoginUserUpdatePasswordCaptcha = (): AxiosPromise => {
    return axios.get('/captcha/getCaptcha',{params: {captchaScene: '/user-identifier-pwd/login/identifier-pwd-update'}})
}
