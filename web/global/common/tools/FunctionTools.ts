/**
 * 是否函数
 * @param fun 函数定义
 */
export function isFunction(fun: any):boolean {
    return typeof fun === "function"
}

/**
 * 防抖函数，拷贝自lodash
 * @param func
 * @param wait
 * @param immediate
 * @return {function(): *}
 */
export function debounce(func: ()=>any, wait:number, immediate:boolean):()=>any {
    let args,
        result,
        thisArg,
        timeoutId;

    function delayed() {
        timeoutId = null;
        if (!immediate) {
            result = func.apply(thisArg, args);
        }
    }
    return function() {
        let isImmediate = immediate && !timeoutId;
        args = arguments;
        thisArg = this;

        clearTimeout(timeoutId);
        timeoutId = setTimeout(delayed, wait);

        if (isImmediate) {
            result = func.apply(thisArg, args);
        }
        return result;
    };
}
