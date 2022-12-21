/**
 * 数据相关，主要是 v-model 封装相关
 */
import {nextTick} from 'vue'
import {isArray} from "../../common/tools/ArrayTools";

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
    // 仅限有选项数据时使用，用来派发选中的数据
    updateModelData: 'update:modelData',
}



// 值更新事件
export const updateDataModelValueEventHandle = ({reactiveData,hasPermission,emit,eventName}) => {
    return function (value){
        if(hasPermission !== undefined ){
            let doAlertOrCustomFnIfNeccessaryResult = hasPermission.value.doAlertOrCustomFnIfNeccessary()
            if (doAlertOrCustomFnIfNeccessaryResult) {
                // 没有权限时，在下一次更新将原来值重置回来，不能编辑
                nextTick(() => {
                    reactiveData.currentModelValue = reactiveData.oldModelValue
                })
                return true
            }
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
        if (hasPermission !== undefined && hasPermission.value.enable && !hasPermission.value.hasPm) {
            // 没有权限时，在下一次更新将原来值重置回来，不能编辑
            nextTick(() => {
                reactiveData.currentModelValue = reactiveData.oldModelValue
            })
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

export interface PushCurrentModelDataParam{
    data: Array<any>,
    value:any| any[],
    result: any[],
    childrenKey:string,
    valueKey: string
}

// 仅限有选项的数据使用，以获取选中的数据对象，需配合watch来获取选中的数据对象
export const pushCurrentModelData = ({data,value,result,childrenKey,valueKey}: PushCurrentModelDataParam) => {
    const isValArray = isArray(value)
    data.forEach((item,index)=>{
        if (isValArray) {
            if(value.some(value => value === item[valueKey])){
                result.push(item)
            }
        }else {
            if(item[valueKey] === value){
                result.push(item)
            }
        }
        if(item[childrenKey] !== undefined){
            pushCurrentModelData({data: item[childrenKey],value,result,childrenKey,valueKey})
        }
    })
}