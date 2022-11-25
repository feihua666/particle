/**
 * 设置本地存储
 * @param key key
 * @param value js对象，会被转为json字符串
 */
export function set(key, value) {
    localStorage.setItem(key, JSON.stringify(value))
}

/**
 * 原生设置本地存储
 * @param key
 * @param value
 */
export function setRaw(key, value) {
    localStorage.setItem(key, (value))
}

/**
 * 获取本地存储
 * @param key
 * @returns json对象
 */
export function get(key) {
    return JSON.parse(localStorage.getItem(key))
}

/**
 * 原生获取本地存储
 * @param key
 * @returns {string}
 */
export function getRaw(key) {
    return (localStorage.getItem(key))
}

/**
 * 清除一个key数据
 * @param key
 */
export function remove(key) {
    localStorage.removeItem(key)
}

/**
 * 清除所有本地存储数据
 */
export function clear() {
    localStorage.clear()
}

/********************* 以下是session storage 这在关闭浏览器后会清空  **********************/


/**
 * session 存储设置
 * @param key key
 * @param value js对象，会被转为json字符串
 */
export function sessionSet(key, value) {
    sessionStorage.setItem(key, JSON.stringify(value))
}

/**
 * session 原生数据存储
 * @param key
 * @param value
 */
export function sessionSetRaw(key, value) {
    sessionStorage.setItem(key, (value))
}

/**
 * session 数据存储获取
 * @param key
 * @returns {any}
 */
export function sessionGet(key) {
    return JSON.parse(sessionStorage.getItem(key))
}

/**
 * session 原生数据存储获取
 * @param key
 * @returns {string}
 */
export function sessionGetRaw(key) {
    return (sessionStorage.getItem(key))
}

/**
 * session 移除数据
 * @param key
 */
export function sessionRemove(key) {
    sessionStorage.removeItem(key)
}

/**
 * session 清空数据
 */
export function sessionClear() {
    sessionStorage.clear()
}

/**
 * session 根据 key 清空数据
 * @param array 数组，如：['abc','xyz']
 */
export function sessionClearKeys(array) {
    if (array) {
        array.forEach(itemKey => {
            sessionStorage.removeItem(itemKey)
        })
    }
}
