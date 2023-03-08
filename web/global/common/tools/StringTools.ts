import {isArray} from './ArrayTools'
import {anyObj} from "./ObjectTools";

/**
 * 判断是否为字符串
 * @param obj
 * @returns {boolean}
 */
export function isString(obj: any):boolean {
    return (typeof obj == 'string')
}

/**
 * 首尾去空格
 * @param str
 * @return {string}
 */
export function trim(str: string): string {
    let reg = /(^\s+)|(\s+$)/g; // 匹配首尾空格
    if (typeof(str) === 'string') {
        return str.replace(reg,'')
    }
}
/**
 * 首字母大写
 * @param str
 * @returns {string}
 */
export function upperFirst(str: string): string {
    return str.charAt(0).toUpperCase() + str.slice(1)
}
/**
 * 首字母小写
 * @param str
 * @returns {string}
 */
export function lowerFirst(str: string): string {
    return str.charAt(0).toLowerCase() + str.slice(1)
}
/**
 * 判断字符器是否以任何一个suffix结尾
 * @param str
 * @param suffix Sring | 数组
 * @returns {boolean}
 */
export function endWithAny(str: string,suffix: string|string[],ignoreCase: boolean) {

    let r = false
    let array = []
    if (isString(suffix)) {
        array.push(suffix)
    }else if (isArray(suffix)) {
        array = (suffix)
    }
    r = array.some((item)=>{
        if(ignoreCase){
            return str.toLowerCase().lastIndexOf(item.toLowerCase()) >= 0
        }else{
            return str.lastIndexOf(item) >= 0
        }
    })
    return r
}

/**
 * 字符串替换，方法名以 b 结尾表示带括号(变量以大括号包裹)
 * @param tempStr fsdfsdsf{id}sdfsdsd
 * @param obj {id: 222}
 * @return 替换好的字符串
 */
export function replaceb(tempStr: string,obj: anyObj): string {
    let r = tempStr
    for(let key in obj){
        r = r.replace('{'+ key +'}',obj[key])
    }
    return r
}
/**
 * 字符串替换，方法名以 c 结尾表示带冒号
 * @param tempStr fsdfsdsf:idsdfsdsd
 * @param obj {id: 222}
 * @return 替换好的字符串
 */
export function replacec(tempStr: string,obj: anyObj): string {
    let r = tempStr
    for(let key in obj){
        r = r.replace(':'+ key,obj[key])
    }
    return r
}

/**
 * 文本
 * @param text 文本
 * @param from 待替换文本
 * @param to 替换的文本
 * @param global 是否全部，否则只匹配第一个
 */
export function replace(text: string,from: string,to: string,golbal?: boolean): string{
    if (golbal) {
        return text.replace(new RegExp(from,'g'),to)
    }
    return text.replace(from,to)
}


/**
 * 下划线转驼峰
 * @param str 带有下划线的字符串
 * @param addtionalSplitStr 将额外的分隔符也转为驼峰，如：['-']
 */
export function underlineToHump(str: string,addtionalSplitStr=[]): string{
    if(!str){
        return str
    }
    addtionalSplitStr.forEach(item => {
        str = str.replaceAll(item,'_')
    })
    let a = str.split("_");
    let result = a[0];
    for(let i=1;i<a.length;i++){
        result = result + a[i].slice(0,1).toUpperCase() + a[i].slice(1);
    }
    return result
}


/**
 * 驼峰转下划线
 * @param str
 */
export function humpToUnderline(str: string): string{
    return str.replace(/([A-Z])/g,"_$1").toLowerCase()
}

