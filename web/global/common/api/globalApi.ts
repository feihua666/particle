import axios, { AxiosPromise} from 'axios'
import {
    getUploadUrl as getUploadUrlRequest,
    getDownloadPrefixUrl as getDownloadPrefixUrlRequest,
    getFaviconUrl as getFaviconUrlRequest,
    getLogoUrl as getLogoUrlRequest,
    getLogoTextUrl as getLogoTextUrlRequest,
    getWebTitleUrl as getWebTitleUrlRequest,
} from "../../pc/common/axios/axiosRequest";
/**
 * 文件上传的url地址
 */
export const getUploadUrl = () => {
    return getUploadUrlRequest()
}
/**
 * 文件下载地址前缀
 */
export const getDownloadPrefixUrl = () => {
    return getDownloadPrefixUrlRequest()
}
/**
 * 文件最终的下载地址
 * @param url
 */
export const getFinalDownloadUrl = (url: string) => {
    if (!url) {
        return url
    }
    if(url.indexOf('blob:/') == 0 || url.indexOf('http') == 0  || url.indexOf('data:') == 0){
        return url
    }
    return getDownloadPrefixUrl() + url
}
/**
 * 获取 favicon.ico，后端支持动态配置图标
 */
export const getFaviconUrl = () => {
    return getFaviconUrlRequest()
}
/**
 * 获取 logo 地址
 */
export const getLogoUrl = () => {
    return getLogoUrlRequest()
}
/**
 * 获取 logo 返回的是图片文件流
 * @param data
 */
export const getLogo = (): AxiosPromise => {
    return axios.get(getLogoUrl(),{configOptions: {showErrorMessage: false}})
}
/**
 * 获取 logo text 地址
 */
export const getLogoTextUrl = () => {
    return getLogoTextUrlRequest()
}
/**
 * 获取 web title 地址
 */
export const getWebTitleUrl = () => {
    return getWebTitleUrlRequest()
}
/**
 * 获取 favicon 返回的是图片文件流
 * @param data
 */
export const getFavicon = (): AxiosPromise => {
    return axios.get(getFaviconUrl(),{configOptions: {showErrorMessage: false}})
}
/**
 * 获取 logo text
 * @param data
 */
export const getLogoText = (): AxiosPromise => {
    return axios.get(getLogoTextUrl(),{configOptions: {showErrorMessage: false}})
}
/**
 * 获取 web title
 * @param data
 */
export const getWebTitle = (): AxiosPromise => {
    return axios.get(getWebTitleUrl(),{configOptions: {showErrorMessage: false}})
}
/**
 * 下载
 * @param data
 */
export const download = (httpUrl: string): AxiosPromise => {
    // 添加预期类型后，如果报错，则在全局的axiosRequest拦截中处理有一些问题，导致错误提示信息无法显示
    return axios.get(httpUrl,{responseType: 'blob'})
}
