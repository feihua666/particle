/**
 * 禁用相关
 */
import {computed} from 'vue'

/**
 * 禁用相关，封装组件时用
 */
export const disabledProps = {
    // disabled
    disabled: {
        type: Boolean,
        default: false
    },
    // 禁用原因
    disabledReason: {
        type: String,
        default: '已禁用'
    },
}
export interface DisabledConfigResult{
    disabled: boolean,
    disabledReason: string
}
// 是否禁用
export const disabledConfig = ({props,dataLoading,hasPermission}: {}):DisabledConfigResult => {

    return computed(() => {
        let disabled = false
        let disabledReason = null
        if (dataLoading && dataLoading.value) {
            disabled = true
            disabledReason = '加载中，请耐心等待'
        }
        if (hasPermission &&  hasPermission.value.disabled) {
            disabled = true
        }
        if(hasPermission && hasPermission.value.enable && !hasPermission.value.hasPm){
            disabledReason = hasPermission.value.noPermissionText
        }
        if (props.disabled) {
            disabled = true
            disabledReason = props.disabledReason
        }

        return {
            disabled,
            disabledReason,
        }
    })
}

