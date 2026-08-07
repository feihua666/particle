/**
 * 是否函数
 * @param fun 函数定义
 */
export function isFunction(fun: any):boolean {
    return typeof fun === "function"
}

/**
 * 防抖函数，拷贝自lodash，并添加了类型
 * @param func
 * @param wait
 * @param immediate
 * @return {function(): *}
 */
export function debounce<T extends (...args: any[]) => any>(func: T, wait: number, immediate: boolean): T {
    let args: IArguments,
        result: ReturnType<T>,
        thisArg: any,
        timeoutId: any;

    function delayed() {
        timeoutId = null;
        if (!immediate) {
            result = func.apply(thisArg, args);
        }
    }
    return function(this: any, ...args: Parameters<T>) {
        let isImmediate = immediate && !timeoutId;
        const context = this;
        const callArgs = args;

        clearTimeout(timeoutId);
        timeoutId = setTimeout(delayed, wait);

        if (isImmediate) {
            result = func.apply(context, callArgs);
        }
        return result;
    } as T;
}
