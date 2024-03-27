import {copy as copyp} from "../../common/tools/ClipboardTools";
import {ElMessage,ElMessageBox} from 'element-plus'
let alert = (message,type='success')=>{
    ElMessage({
        showClose: true,
        message: message,
        type: type,
        showIcon: true,
        grouping: true
    })
}
export function copy(text: string,showManualBox: boolean=true){
    copyp(text).then(res=>{
        alert("已复制！")
    }).catch(err=>{
        alert("复制失败！",'error')
        if(showManualBox){
            ElMessageBox.alert(text, '复制失败,请手动复制', {
                confirmButtonText: '确定',
            })
        }

    })
}