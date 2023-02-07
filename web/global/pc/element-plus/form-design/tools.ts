import {ref} from "vue";
import {loadScriptCode} from "../../../common/tools/DocumentTools";

/**
 * 将字符串函数或json转为js函数并可执行
 * @param strCode
 * @param defaultVal
 */
function doStrCodeTocode(strCode,defaultVal){
    if(strCode){
        // 有脚本执行能力安全风险
        loadScriptCode("window.strCodeTocode = " + strCode)
        return window.strCodeTocode
    }
    return defaultVal
}
function doCodeToStrCode(code,defaultVal){
    if(code){
       try {
           return JSON.stringify(code)
       }catch (e) {

       }
    }
    return defaultVal
}
// 以响应试导出，因为在vue 渲染过程中不能直接使用原函数
export const strCodeTocode = ref(doStrCodeTocode)
export const codeToStrCode = ref(doCodeToStrCode)
