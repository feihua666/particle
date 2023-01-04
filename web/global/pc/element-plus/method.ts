/**
 * 事件触发相关
 */

import {isPromise} from "../../common/tools/PromiseTools"
import {isFunction} from "../../common/tools/FunctionTools"
import {isString} from "../../common/tools/StringTools"
import {ElMessage, ElMessageBox} from 'element-plus'

let alert = (message,type='success')=>{
    ElMessage({
        showClose: true,
        message: message,
        type: type,
        showIcon: true,
        grouping: true
    })
}

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
    // 在调用method之前判断是否要调用，返回 true表示要调用，false不调用，如果返回非空字符串，提示alert
    beforeMethod: {
        type: Function,
        default: () => true
    }
    ,
    // method 回调时的参数
    methodParam: {
        type: Object
    },
    // 点击按钮弹层确认文本，如果不传值将不会弹窗确认
    methodConfirmText: {
        type: String
    },
    // 成功提示或操作
    methodSuccess: {
        type: [String,Function]
    },
    // 启动全屏 loading 效果
    methodLoadingFullScreen: {
        type: Boolean,
        default: false
    }
}
// 属性,不能直接导出对象使用，导出意味着单例，会有状态，导致数据共享造成数据不一致
export const reactiveMethodData = () => ({
    methodLocalLoading: false,
})
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
        let beforeResult = props.beforeMethod()
        if(beforeResult !== true){
            if (isString(beforeResult)) {
                alert(beforeResult,'error')
            }

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
const methodSuccess = (methodSuccess)=>{
    if(methodSuccess){
        let message = methodSuccess
        if (isFunction(methodSuccess)) {
            message = methodSuccess()
        }
        if (isString(message) ) {
            alert(message)
        }
    }
}
// 发起调用
export const doMethod = ({props,reactiveData,emit}: {}) =>{

    return function () {
        let args = arguments
        reactiveData.methodLocalLoading = true
        if (props.method) {

            let result =  null
            if(props.methodParam){
                result = props.method(props.methodParam,...args)
            }else {
                result = props.method(...args)
            }
            if (isPromise(result)) {
                const promiseResult = result.then(res =>{
                    methodSuccess(props.methodSuccess)
                    return Promise.resolve(res)
                }).catch(error => {
                    return Promise.reject(error)
                }).finally(()=>{
                    reactiveData.methodLocalLoading = false
                })
                if(emit){
                    emit(emitMethodEvent.methodResult,promiseResult)
                }
            }else {
                methodSuccess(props.methodSuccess)
                if(emit){
                    emit(emitMethodEvent.methodResult,result)
                }
                reactiveData.methodLocalLoading = false
            }
        }else {
            reactiveData.methodLocalLoading = false
        }
    }
}