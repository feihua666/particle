import axios, {type AxiosPromise} from 'axios'
import type {anyObj} from "../../../../global/common/tools/ObjectTools";
import type {IdParam} from "../../../../common/api/api";
import {getApiPrefix} from "../../../../common/api/apiPrefixConfig";

let prefix = getApiPrefix(import.meta.env.VITE_API_PREFIX_USER)
/**
 * 登录
 * @param loginForm
 */
export const login = ({username,password,captchaUniqueIdentifier,captchaValue}:{username: string,password: string,captchaUniqueIdentifier: string,captchaValue: string}): AxiosPromise => {
    return axios.post(prefix + '/login', {username,password,captchaUniqueIdentifier,captchaValue},{
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
    return axios.post(prefix + '/loginCaptcha', {username,password,captchaUniqueIdentifier,captchaValue},{
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    })
}
/**
 * 退出登录
 */
export const logout = (): AxiosPromise => {
    return axios.post(prefix + '/logout')
}
/**
 * 判断用户是否登录
 */
export const hasLogin = (): AxiosPromise => {
    return axios.get(prefix + '/hasLogin')
}
/**
 * 获取当前登录用户的信息
 */
export const userinfo = (): AxiosPromise => {
    return axios.get(prefix + '/userinfo')
}


/**
 * 切换租户
 * @param data
 */
export const changeTenant = (data: IdParam): AxiosPromise => {
    return axios.post(prefix + '/changeTenant',data)
}
/**
 * 切换角色
 * @param data
 */
export const changeRole = (data: IdParam): AxiosPromise => {
    return axios.post(prefix + '/changeRole',data)
}
/**
 * 获取登录记录
 */
export const getLoginRecord = (): AxiosPromise => {
    return axios.get(prefix + '/loginRecord')
}
/**
 * 获取登录设备
 */
export const getLoginDevice = (): AxiosPromise => {
    return axios.get(prefix + '/loginDevice')
}
/**
 * 获取登录标识/我的账号
 */
export const getIdentifier = (): AxiosPromise => {
    return axios.get(prefix + '/user-identifier/login/identifier')
}
/**
 * 获取登录标识/我的密码
 */
export const getIdentifierPwd = (): AxiosPromise => {
    return axios.get(prefix + '/user-identifier-pwd/login/identifier-pwd')
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
    return axios.post(prefix + '/user-identifier-pwd/login/identifier-pwd-update',data)
}
/**
 * 获取登录验证码
 */
export const getLoginCaptcha = (): AxiosPromise => {
    return axios.get(prefix + '/captcha/getCaptcha',{params: {captchaScene: '/login'}})
}
/**
 * 获取登录动态验证码
 */
export const getLoginDynamicCaptcha = (data: anyObj): AxiosPromise => {
    return axios.get(prefix + '/captcha/getDynamicCaptcha',{params: {captchaScene: '/loginCaptcha',...data}})
}
/**
 * 获取当前登录用户修改密码验证码
 */
export const getLoginUserUpdatePasswordCaptcha = (): AxiosPromise => {
    return axios.get(prefix + '/captcha/getCaptcha',{params: {captchaScene: '/user-identifier-pwd/login/identifier-pwd-update'}})
}
