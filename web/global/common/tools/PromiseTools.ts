import {isObject} from '../../common/tools/ObjectTools'
import {isFunction} from '../../common/tools/FunctionTools'

/**
 * 是否为 promise
 * @param val 检测对象
 */
export function isPromise(val: any):boolean {
    return isObject(val) && isFunction(val.then) && isFunction(val.catch)
}

/**
 * 执行异步动作并自动管理 loading 状态
 * @param action 要执行的动作
 * @param onLoading loading 状态回调
 * @returns 执行结果
 */
export async function withLoading<T>(
    action: () => T | Promise<T>,
    onLoading?: (loading: boolean) => void
): Promise<T> {
    onLoading?.(true)
    try {
        return await Promise.resolve(action())
    } finally {
        onLoading?.(false)
    }
}
