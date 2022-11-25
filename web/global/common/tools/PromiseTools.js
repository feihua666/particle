import {isObject} from '../../common/tools/ObjectTools.js'
import {isFunction} from '../../common/tools/FunctionTools.js'
/**
 * 是否为 promise
 * @param val 检测对象
 */
export function isPromise(val) {
    return isObject(val) && isFunction(val.then) && isFunction(val.catch)
}