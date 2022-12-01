import type {anyObj} from "./ObjectTools"

/**
 * 设置本地存储
 * @param key key
 * @param value js对象，会被转为json字符串
 */
export function set(key: string, value: anyObj): void {
    localStorage.setItem(key, JSON.stringify(value))
}

/**
 * 原生设置本地存储
 * @param key
 * @param value
 */
export function setRaw(key: string, value: string) {
    localStorage.setItem(key, (value))
}

/**
 * 获取本地存储
 * @param key
 * @returns json对象
 */
export function get(key: string): anyObj {
    return JSON.parse(localStorage.getItem(key))
}

/**
 * 原生获取本地存储
 * @param key
 * @returns {string}
 */
export function getRaw(key: string):string {
    return (localStorage.getItem(key))
}

/**
 * 清除一个key数据
 * @param key
 */
export function remove(key: string): void {
    localStorage.removeItem(key)
}

/**
 * 清除所有本地存储数据
 */
export function clear(): void {
    localStorage.clear()
}

/********************* 以下是session storage 这在关闭浏览器后会清空  **********************/


/**
 * session 存储设置
 * @param key key
 * @param value js对象，会被转为json字符串
 */
export function sessionSet(key: string, value: string):void {
    sessionStorage.setItem(key, JSON.stringify(value))
}

/**
 * session 原生数据存储
 * @param key
 * @param value
 */
export function sessionSetRaw(key: string, value: string):void {
    sessionStorage.setItem(key, (value))
}

/**
 * session 数据存储获取
 * @param key
 * @returns {any}
 */
export function sessionGet(key: string): anyObj {
    return JSON.parse(sessionStorage.getItem(key))
}

/**
 * session 原生数据存储获取
 * @param key
 * @returns {string}
 */
export function sessionGetRaw(key: string): string {
    return (sessionStorage.getItem(key))
}

/**
 * session 移除数据
 * @param key
 */
export function sessionRemove(key: string): void {
    sessionStorage.removeItem(key)
}

/**
 * session 清空数据
 */
export function sessionClear():void {
    sessionStorage.clear()
}

/**
 * session 根据 key 清空数据
 * @param array 数组，如：['abc','xyz']
 */
export function sessionClearKeys(array: string[]) {
    if (array) {
        array.forEach(itemKey => {
            sessionStorage.removeItem(itemKey)
        })
    }
}
