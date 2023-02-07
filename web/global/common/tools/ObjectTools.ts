export interface anyObj {
    [key: string]: any
}
/**
 * 判断是否为对象
 * promis array map等都是对象
 * @param obj
 * @returns {boolean}
 */
export function isObject(obj: any):boolean {
    return obj !== null && typeof obj === 'object'
}
/**
 * 判断是否为纯对象
 * @param obj
 * @returns {boolean}
 */
export function isPlainObject(obj: any):boolean  {
    return Object.prototype.toString.call(obj) === '[object Object]'
}
/**
 * 是否为空对象
 * @param obj
 * @return {boolean}
 */
export function isEmpty(obj: anyObj):boolean  {
    return Object.keys(obj).length <= 0
}
/**
 * 对象克隆
 * 该克隆只能克隆简单数据对象，如果值有函数，undefined 等将会有问题，要么为空没有那个属性 ，要么属性值为空等等
 * 如果需要保留建议使用 cloneObj ，遍历方式
 * @param obj
 */
export function clone(obj: anyObj):anyObj {
    return JSON.parse(JSON.stringify(obj))
}

/**
 *  遍历克隆
 * @param obj
 */
export function cloneObj (obj: any): any {
    let newObj = obj instanceof Array ? [] : (isObject(obj) ? {} : obj)
    for (let key in obj) {
        let val = obj[key]
        newObj[key] = typeof val === 'object' ? cloneObj(val) : val
    }
    return newObj
}
/**
 * 将属性为空串的字符串，设置为null
 * 主要是为了查询时空串也查询问题
 * @param obj
 */
export function emptyToNull(obj: anyObj): anyObj{
    if(obj){
        for (let key in obj) {
            if(obj[key] === ''){
                obj[key] = null
            }
        }
    }
    return obj
}
/**
 * 对象合并
 * @param obj
 */
export function extend(target: object, ...sources: any[]): anyObj {
    return Object.assign(...sources)
}

/**
 * 深度合并
 * @param sources
 */
export function extendDeep(...sources: anyObj[]): anyObj {

    let extendD = function(obj1,obj2){

        let obj3 = extend({},obj1,obj2)
        // 定义一个临时对象
        let temp = {}
        // 遍历两个对象判断对象是否存在
        for(let name in obj1){
            if (isObject(obj1[name])) {
                temp[name] = [true,false]
            }
        }
        for(let name in obj2){
            if (isObject(obj2[name])) {
                if(temp[name]){
                    temp[name][1] = true
                }else {
                    temp[name] = [false,true]
                }
            }
        }
        // 将结果再次合并
        for(let name in temp){
            if(temp[name][0] && temp[name][1]){
                obj3[name] = extendD(obj1[name],obj2[name])
            }else if(temp[name][0] && !temp[name][1]){
                obj3[name] = obj1[name]
            }else {
                obj3[name] = obj2[name]
            }
        }
        return obj3
    }
    let result = {}
    let length = sources.length
    for (let i = 0; i < length; i++) {
        result = extendD(result,sources[i])
    }

    return result
}
/**
 * 深度获取对象属性
 * @param obj
 * @param prop 字符串 属性名称 如：a.b.c
 * @returns {*}
 */
export function getValue(obj: anyObj, prop: string):any {
    if (obj && prop) {
        let value = obj
        let key = prop.split('.')
        for (let i = 0; i < key.length; i++) {
            value = value[key[i]]
            if (value === null || value === undefined) {
                return value
            }
        }
        return value
    }
    return null
}

/**
 * 获取对象的值，支持方法调用
 * @param obj
 * @param prop
 * @param param 如果值为方法，该参数为方法的参数
 * @returns {null|*}
 */
export function getVal(obj: anyObj, prop: string,param: any):any {
    if (obj && prop) {
        let value = obj[prop]
        if(typeof value == 'function'){
            return value(param)
        }
        return value
    }
    return null
}
/**
 * 是否有属性
 * @param obj 对象
 * @param prop 属性名
 */
export function hasProp(obj: anyObj,prop: string):boolean {
    if (obj && prop) {
        let value = obj
        let key = prop.split('.')
        for (let i = 0; i < key.length; i++) {
            value = value[key[i]]
            if (value == undefined) {
                return false
            }
        }
        return true
    }
    return false
}
/**
 * 是否有属性
 * 比 hasProp 更准确
 * @param obj 对象
 * @param prop 属性名
 */
export function hasOwnProps(obj: anyObj,prop: string):boolean {
    if (obj && prop) {
        let value = obj
        let key = prop.split('.')
        for (let i = 0; i < key.length; i++) {
            if (isObject(value)) {
                let r = value.hasOwnProperty(key[i])
                if(r === false){
                    return r
                }
                if(i == key.length - 1){
                    return true
                }
                value = value[key[i]]
            }
        }
    }
    return false
}

/**
 * 添加属性
 * @param obj
 * @param prop 支持点操作符如：a.b
 */
export function addProp(obj: anyObj,prop: string,value: any):void{
    let valueTemp = obj
    let parent = obj
    let key = prop.split('.')
    for (let i = 0; i < key.length; i++) {
        parent = valueTemp
        valueTemp = parent[key[i]]
        if (valueTemp === null || valueTemp === undefined) {
           parent[key[i]] = {}
            valueTemp = parent[key[i]]
        }
        if(i == key.length - 1 && isObject(parent)){
           parent[key[i]] = value
        }
    }
}

/**
 * 根据值获取key
 * @param obj
 * @param value
 */
export function getPropByValue(obj: anyObj,value: any):string{
    for (let prop in obj) {
        if(obj[prop] == value){
            return prop
        }
    }
    return undefined
}

/**
 * 判断一个对象属性key是否存在，如果存在返回一个新的带后缀的key
 * @param obj
 * @param prop
 * @param suffix
 */
export function getNewProp(obj: anyObj,prop: string,suffix: string):string{
    if (hasOwnProps(obj, prop)) {
        return getNewProp(obj,prop + suffix,suffix)
    }
    return prop
}