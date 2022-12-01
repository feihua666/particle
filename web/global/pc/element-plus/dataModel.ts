/**
 * 数据相关，主要是 v-model 封装相关
 */
import {nextTick} from 'vue'

/**
 * 数据相关，封装组件时用
 */
export const dataModelProps = {
}
// 属性
export const reactiveDataModelData = (props) => {
    return {
        oldModelValue: props.modelValue,
        currentModelValue: props.modelValue
    }
}
export const emitDataModelEvent = {
    updateModelValue: 'update:modelValue',
    change: 'change',
}



// 值更新事件
export const updateDataModelValueEventHandle = ({reactiveData,hasPermission,emit,eventName}) => {
    return function (value){
        let doAlertOrCustomFnIfNeccessaryResult = hasPermission.value.doAlertOrCustomFnIfNeccessary()
        if (doAlertOrCustomFnIfNeccessaryResult) {
            // 没有权限时，在下一次更新将原来值重置回来，不能编辑
            nextTick(() => {
                reactiveData.currentModelValue = reactiveData.oldModelValue
            })
            return true
        }
        reactiveData.currentModelValue = value
        reactiveData.oldModelValue = value
        let params = [eventName ? eventName: emitDataModelEvent.change]
        for (let i = 0; i < arguments.length; i++) {
            params.push(arguments[i])
        }
        emit.apply(null,params)
        return false
    }
}
// 值改变事件,
export const changeDataModelValueEventHandle = ({reactiveData,hasPermission,emit,eventName}) => {

    return function (value) {
        if (hasPermission.value.enable && !hasPermission.value.hasPm) {
            return true
        }
        let params = [eventName ? eventName: emitDataModelEvent.updateModelValue]
        for (let i = 0; i < arguments.length; i++) {
            params.push(arguments[i])
        }
        emit.apply(null,params)
        return false
    }
}