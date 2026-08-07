import { ElMessage } from 'element-plus'
import {debounce} from "../../common/tools/FunctionTools";

/**
 * 封装一个显示提示信息工具
 * @param message
 * @param type
 */
export function showMsg (message: string,type='success'): void{
    ElMessage({
        showClose: true,
        message: message,
        type: type,
        showIcon: true,
        grouping: true
    })
}

/**
 * 创建一个新的防抖消息提示实例
 * @param wait 等待时间（毫秒）
 * @param immediate 是否立即执行
 */
export function newDebouncedShowMsg(wait = 500, immediate = true) {
    return debounce((msg: string, type: string) => {
        showMsg(msg, type)
    }, wait, immediate)
}

/**
 * 防抖的消息提示函数（500ms 内相同显示一次）
 */
const debouncedShowMsg = newDebouncedShowMsg()
/**
 * 封装一个显示提示信息工具，使用防抖
 * @param message
 * @param type
 */
export function showDMsg(message: string, type = 'success'): void {
    debouncedShowMsg(message, type)
}
