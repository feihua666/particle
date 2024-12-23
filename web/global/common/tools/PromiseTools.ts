import {isObject} from '../../common/tools/ObjectTools'
import {isFunction} from '../../common/tools/FunctionTools'

/**
 * 是否为 promise
 * @param val 检测对象
 */
export function isPromise(val: any):boolean {
    return isObject(val) && isFunction(val.then) && isFunction(val.catch)
}
