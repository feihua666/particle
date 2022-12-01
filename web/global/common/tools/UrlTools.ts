import {anyObj} from "./ObjectTools";

export function getUrlParam (name: string, url: string): string {
    //构造一个含有目标参数的正则表达式对象
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)")
    let r;
    if (url) {
        r = url.substr(url.indexOf("?"));
    } else {
        r = window.location.search;
    }
    //匹配目标参数
    r = r.substr(1).match(reg);
    //返回参数值
    if (r != null){
        return decodeURI(r[2])
    }
    return null;
}

/**
 * url拼接参数
 * @param url
 * @param params
 * @return {string}
 */
export function appendParam (url: string,params: anyObj={}): string {
    let r = url
    let p = []
    for (let key in params) {
        if(params[key] !== '' && params[key] !== undefined){
            p.push(key + '=' + params[key])
        }
    }
    return url + (url.indexOf('?') >0 ? '&' : '?') + p.join('&')
}

/**
 * 获取当前地址栏的域名
 * @param withProtocl true = 包括协议全路径
 */
export function getCurrentDomain(withProtocl: boolean = true) {
    let r = window.location.host
    let split = '//'
    if(withProtocl){
        let href = window.location.href
        let splitArray = href.split(split)
        if(splitArray.length > 0){
            r = splitArray[0] + split + r
        }
    }
    return r
}