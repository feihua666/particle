import axios from '@nm/axios'
import { ElMessage ,ElNotification} from '@nm/element-plus'
import {isFunction} from '../../../common/tools/FunctionTools'
// 用户未登录时使用
import {useLoginUserStore} from '../../../common/security/loginUserStore.js'
import {anyObj} from "../../../common/tools/ObjectTools";
// store 变量缓存
let loginUserStoreCache = null
/**
 * axios 简单封装
 * 参考：https://juejin.cn/post/6968630178163458084?share_token=7831c9e0-bea0-469e-8028-b587e13681a8#heading-27
 */
/**
 * 请求中的请求保存
 * @type {Map<any, any>}
 */
const pendingMap = new Map()

/*
 * 根据运行环境获取基础请求URL
 */
export const getUrl = (): string => {
    const value = import.meta.env.VITE_AXIOS_BASE_URL
    return value == 'getCurrentDomain' ? window.location.protocol + '//' + window.location.host : value
}

/*
 * 根据运行环境获取基础请求URL的端口
 */
export const getUrlPort = (): string => {
    const url = getUrl()
    return new URL(url).port
}
export interface Config{
    tokenKey?: string, // tokenKey
    userDefaultInstance?: boolean,// 使用默认axios实例，否则创建新的实例
    baseURI?: string, // 基础url
    timeout?: number,// timeout 单位毫秒 默认 10s
    cancelDuplicateRequest?: boolean, // 是否开启取消重复请求, 默认为 true
    loading?: boolean, // 是否开启loading层效果, 默认为false
    reductDataFormat?: boolean, // 是否开启简洁的数据结构响应, 默认为true
    showErrorMessage?: boolean, // 是否开启接口错误信息展示,默认为true
    showNoneSuccessMessage?: boolean, // 是否开启接口非错误但未成功信息展示,默认为true
    noneSuccessMessage?: (response)=> string, // 未成功时，返回消息
    showErrorMessageView?: ((message: string)=> void)|'alert'|'notification', // 开启接口错误信息展示的视觉方式，alert=顶部提示，notification=通知提示,函数，直接调用
    showNoneSuccessMessageView?: ((message: string)=> void)|'alert'|'notification', // 开启接口错误信息展示的视觉方式，alert=顶部提示，notification=通知提示,函数，直接调用
    alertCallback?: (message: string) => void,
    notificationCallback?: (message: string) => void
}
/**
 * 默认的创建 axios 实例的配置
 */
const defaultConfig: Config = {
    tokenKey: 'c-token-id',
    userDefaultInstance: true,// 使用默认axios实例，否则创建新的实例
    baseURI: getUrl(), // 基础url
    timeout: 10000,// timeout 单位毫秒 默认 10s
    cancelDuplicateRequest: false, // 是否开启取消重复请求, 默认为 false
    loading: false, // 是否开启loading层效果, 默认为false
    reductDataFormat: false, // 是否开启简洁的数据结构响应, 默认为 false
    showErrorMessage: true, // 是否开启接口错误信息展示,默认为true
    showNoneSuccessMessage: true, // 是否开启接口错误信息展示,默认为true
    noneSuccessMessage: (response) => {
        if(response.data && response.data.success !== undefined && response.data.success == false){
            return response.data.errMessage
        }
        return ''
    }, // 是否开启接口错误信息展示,默认为true
    showErrorMessageView: 'alert', // 开启接口错误信息展示的视觉方式，alert=顶部提示，notification=通知提示,函数，直接调用
    showNoneSuccessMessageView: 'alert', // 开启接口错误信息展示的视觉方式，alert=顶部提示，notification=通知提示,函数，直接调用
    alertCallback: (message) => {
        ElMessage({
            showClose: true,
            message: message,
            type: 'error',
            showIcon: true,
            grouping: true
        })
    },
    notificationCallback: (message) => {
        ElNotification({
            type: 'error',
            message,
        })
    }
}

/**
 * 处理异常
 * @param {*} error
 */
function httpErrorStatusHandle(error,config: Config) {
    // 处理被取消的请求
    if (axios.isCancel(error)) return console.error(('axios.Automatic cancellation due to duplicate request:') + error.message)
    let message = ''
    if (error && error.response && error.response.data && error.response.data.errMessage) {
        message = error.response.data.errMessage
    }
    if (error.message.includes('timeout')) message = ('请求超时')
    if (error.message.includes('Network'))
        message = window.navigator.onLine ? ('服务异常') : ('网络已断开')

    if (message) {
        if(isFunction(config.showErrorMessageView)){
            config.showErrorMessageView(message)
        } else if ('alert' == config.showErrorMessageView) {
           config.alertCallback(message)
        }else if('notification' == config.showErrorMessageView){
            config.notificationCallback(message)
        }
    }
}
function httpNoneSuccessHandle(response,config: Config){
    let message = config.noneSuccessMessage(response)
    if(message){
        if(isFunction(config.showNoneSuccessMessageView)){
            config.showNoneSuccessMessageView(message)
        } else if ('alert' == config.showNoneSuccessMessageView) {
            config.alertCallback(message)
        }else if('notification' == config.showNoneSuccessMessageView){
            config.notificationCallback(message)
        }
    }
}

/**
 * 储存每个请求的唯一cancel回调, 以此为标识
 */
function addPending(config) {
    const pendingKey = getPendingKey(config)
    config.cancelToken =
        config.cancelToken ||
        new axios.CancelToken((cancel) => {
            if (!pendingMap.has(pendingKey)) {
                pendingMap.set(pendingKey, cancel)
            }
        })
}

/**
 * 删除重复的请求
 */
function removePending(config) {
    const pendingKey = getPendingKey(config)
    if (pendingMap.has(pendingKey)) {
        const cancelToken = pendingMap.get(pendingKey)
        cancelToken(pendingKey)
        pendingMap.delete(pendingKey)
    }
}

/**
 * 生成每个请求的唯一key
 */
function getPendingKey(config) {
    let { data } = config
    const { url, method, params, headers } = config
    return [
        url,
        method,
        headers,
        JSON.stringify(params),
        JSON.stringify(data),
    ].join('&')
}

/**
 * 根据请求方法组装请求数据/参数
 */
export function requestPayload(method: string, data: anyObj): anyObj {
    if (method == 'GET' || method == 'get') {
        return {
            params: data,
        }
    } else {
        return {
            data: data,
        }
    }
}

// 请求拦截
export const interceptRequest = (axiosInstance,configOptions: Config) => {
    axiosInstance.interceptors.request.use(
        (config) => {
            removePending(config)
            // 如果取消请求添加
            configOptions.cancelDuplicateRequest && addPending(config)

            // 携带 token
            if (config.headers && configOptions.tokenKey) {
                loginUserStoreCache = loginUserStoreCache || useLoginUserStore()
                const token = loginUserStoreCache.token
                if (token){
                    config.headers[configOptions.tokenKey] = token
                }

            }


            return config
        },
        (error) => {
            return Promise.reject(error)
        }
    )
}

// 响应拦截
export const interceptResponse = (axiosInstance, configOptions: Config) => {
    axiosInstance.interceptors.response.use(
        (response) => {
            removePending(response.config)

            if (response.config.responseType == 'json') {
                // todo 接口正常返回额外处理
            }// end if response.config.responseType == 'json'
            loginUserStoreCache = loginUserStoreCache || useLoginUserStore()
            let token = response.headers[configOptions.tokenKey] || ''
            if (token){
                loginUserStoreCache.changeToken(token)
            }

            configOptions.showNoneSuccessMessage && httpNoneSuccessHandle(response,configOptions)
            return configOptions.reductDataFormat ? response.data : response
        },
        (error) => {
            error.config && removePending(error.config)

            if (error && error.response) {
                // 40300000003 为后端匿名登录返回的状态码
                if (error.response.status == 401 || error.response.data.status == 40300000003) {
                    loginUserStoreCache = loginUserStoreCache || useLoginUserStore()
                    loginUserStoreCache.changeHasLogin(false)
                }
            }
            configOptions.showErrorMessage && httpErrorStatusHandle(error, configOptions) // 处理错误状态码
            return Promise.reject(error) // 错误继续返回给到具体页面
        }
    )
}

/**
 * 创建 axios 实例
 * @param config
 * @returns {AxiosInstance}
 */
export const createAxios = (config: Config={}) => {

    // 默认配置覆盖
    let configOptions = Object.assign(defaultConfig,config)
    // 应用配置
    let instance = axios
    if (!configOptions.userDefaultInstance) {
        instance = axios.create()
    }
    instance.defaults.baseURL = configOptions.baseURI
    instance.defaults.timeout = configOptions.timeout;
    // 拦截请求配置
    interceptRequest(instance, configOptions)
    // 拦截响应配置
    interceptResponse(instance, configOptions)
    return instance
}

export default createAxios()