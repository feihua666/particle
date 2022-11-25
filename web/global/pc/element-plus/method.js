/**
 * 事件触发相关
 */

import {isPromise} from "../../common/tools/PromiseTools"
import { ElMessageBox } from 'element-plus'

/**
 * 数据加载相关，封装组件时用
 */
export const methodProps = {
    // 加载数据处理方法，一般为http请求获取数据，会自动处理 loading 效果，一般返回 promise，也可以直接返回数据本身
    // 自动方法调用函数，会自动处理 loading 效果 一般返回 promise，可以代替 click 事件
    // 因为未找到 click 事件的包装方式，所以自定义一个函数传参实现
    method: {
        type: Function
    },
    // 点击按钮弹层确认文本，如果不传值将不会弹窗确认
    methodConfirmText: {
        type: String
    },
    // 启动全屏 loading 效果
    methodLoadingFullScreen: {
        type: Boolean,
        default: false
    }
}
// 属性
export const reactiveMethodData = {
    methodLocalLoading: false,
}
export const emitMethodEvent = {
    methodResult: 'methodResult'
}
export const method = ({props,reactiveData,emit,hasPermission,doMethod: doMethodFn}) => {

    let doMethodFun = doMethodFn || doMethod({props,reactiveData,emit})
    return function () {
        let args = arguments
        let doAlertOrCustomFnIfNeccessaryResult = hasPermission.value.doAlertOrCustomFnIfNeccessary()
        if (doAlertOrCustomFnIfNeccessaryResult) {
            return
        }

        if(props.methodConfirmText){
            ElMessageBox.confirm(
                props.methodConfirmText,
                '提示',
                {
                    confirmButtonText: '确认',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
            ).then(() => {
                // 确认框确定事件
                doMethodFun.apply(null,args)
            }).catch(() => {
                // 确认框取消事件
            })
        }else {
            doMethodFun.apply(null,args)
        }
    }
}
// 加载数据
export const doMethod = ({props,reactiveData,emit}) =>{

    return function () {
        let args = arguments
        reactiveData.methodLocalLoading = true
        if (props.method) {
            const result =  props.method()
            if (isPromise(result)) {
                const promiseResult = result.then(res =>{
                    return Promise.resolve(res)
                }).catch(error => {
                    return Promise.reject(error)
                }).finally(()=>{
                    reactiveData.methodLocalLoading = false
                })
                emit(emitMethodEvent.methodResult,promiseResult)
            }else {
                emit(emitMethodEvent.methodResult,result)
                reactiveData.methodLocalLoading = false
            }
        }else {
            reactiveData.methodLocalLoading = false
        }
    }
}