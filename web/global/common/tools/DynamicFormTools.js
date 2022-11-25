import {clone, extend, isObject} from "./ObjectTools"

/**
 * 根据类型重新整理动态表单
 * @param originOptions
 * @param type
 * @return {*}
 */
export function options(originOptions,type) {
    if(!type){
        return originOptions
    }
    let r = []
    originOptions.forEach(item => {
        if (isObject(item)) {
            if(item[type] === false){
                return
            }
            // 不存在直接加入
            if(!item[type]){
                r.push(item)
            }else {
                // 存在需要替换自定义的配置
                let newItem = clone(item)
                newItem.element = extend({},item.element,item[type].element)
                newItem.field = extend({},item.field,item[type].field)

                r.push(newItem)
            }

        }else{
            // 数组
            r.push(options(item,type))
        }
    })
    return r
}