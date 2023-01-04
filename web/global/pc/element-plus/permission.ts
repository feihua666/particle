/**
 * 权限相关,主要用于组件封装
 */
import { computed} from 'vue'
import {isString} from "../../common/tools/StringTools"
import {exist} from "../../common/tools/ArrayTools"
import {isFunction} from "../../common/tools/FunctionTools"
import { ElMessage } from 'element-plus'

/**
 * 权限属性，封装组件时用
 */
export const permissionProps = {
    // 权限 支持 string权限码（需要配置注入权限或pemissions属性）和自定义函数返回boolean来决定是否有权限
    permission: {
        type: [String,Function],
    },
    // 所有的权限，数据元素项为 String类型权限码，配合permission属性判断是否有权限
    permissions: {
        type: Array,
        default: ()=>[]
    },
    // 没有权限时表现 可用值：disabled=禁用，hide|notRender=不展示，alert=弹窗提示，customFn=自定义函数触发（noPermissionFn属性）
    noPermissionView: {
        type: String,
        default: 'disabled'
    },
    // 没有权限时且noPermissionView=customFn，触发
    noPermissionFn: {
        type: Function,
        default: () => {}
    },
    // 没有权限的提示,拼接没有权限提示语句，如：您没有 + noPermissionSimpleText + 权限
    noPermissionSimpleText: {
        type: String,
        default: undefined
    },
    // 没有权限的提示
    noPermissionText: {
        type: String,
        default: undefined
    }
}
/**
 * 检查是否有权限
 * @param permission
 * @param permissions
 */
const hasPermissionCheck = (permission: string,permissions: Array<string>| { }):boolean =>{
    let permissionsTemp = permissions
    if(permissionsTemp.value){
        permissionsTemp = permissionsTemp.value
    }
    // 匹配任何权限
    let any = '*';
    let r = permission == any
    // 可用权限 包含是否包括 *
    if(r == false){
        r = exist(any,permissionsTemp)
    }
    if(r == false){
        r = exist(permission,permissionsTemp)
    }

    if(r == false){
        r = permissionsTemp.some(item => exist(permission,item.split(',')))
    }
    return r
}
// 返回是否有权限，及权限其它常用属性
export const hasPermissionConfig = ({props,injectPermissions,noPermissionSimpleText}) => {

    return computed(() => {
        // 是否开启权限判断
        let enable = false
        let hasPm = false
        if (props.permission !== undefined) {
            // 传了权限表示开启
            enable = true
            if (isString(props.permission)) {
                hasPm = hasPermissionCheck(props.permission,props.permissions) || hasPermissionCheck(props.permission,injectPermissions)
            }else if(isFunction(props.permission)){
                hasPm = props.permission()
            }else {
                // 只支持 string=权限码和funtion=自定义判断
            }
        }
        let render = true
        let disabled = false

        let noPermissionText = props.noPermissionText
        if(noPermissionText == undefined){
            noPermissionText = `您没有${props.noPermissionSimpleText != undefined ? props.noPermissionSimpleText : (noPermissionSimpleText ? noPermissionSimpleText : '')}权限`
        }
        if (enable && !hasPm) {
            if('notRender' == props.noPermissionView || 'hide' == props.noPermissionView){
                render = false
            }
            if('disabled' == props.noPermissionView){
                disabled = true
            }
        }
        let alert = ()=>{
            ElMessage({
                    showClose: true,
                    message: noPermissionText,
                    type: 'error',
                    showIcon: true,
                    grouping: true
                })
            }
        return {
            enable,
            hasPm,
            render,
            disabled,
            noPermissionText,
            alert,
            // 返回 true=执行了没有权限的操作，false=没有任何操作
            doAlertOrCustomFnIfNeccessary: () => {
                if (enable && !hasPm) {
                    if ('alert' == props.noPermissionView) {
                        alert()
                        return true
                    }
                    
                    if ('customFn' == props.noPermissionView) {
                        props.noPermissionFn()
                        return true
                    }
                    return true
                } 
                return false
            }
        }
    })
}

