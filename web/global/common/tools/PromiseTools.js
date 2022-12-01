import {isObject} from '../../common/tools/ObjectTools.js'
import {isFunction} from '../../common/tools/FunctionTools.js'
/**
 * 是否为 promise
 * @param val 检测对象
 */
export function isPromise(val) {
    return isObject(val) && isFunction(val.then) && isFunction(val.catch)
}

/**
 * 获取 可能是 promise boolean 结果返回
 * @param val 可能会传递一个参数
 * @returns {Promise<void>}
 */
export async function promiseBoolValue(method,val) {
    let result = false
    try {
        result = await method(val)
    } catch {
        result = false
    }
    return result
}