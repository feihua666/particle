<script setup name="DictManageBatchAddPage" lang="ts">
/**
 * 字典管理批量添加页面
 */
import {reactive, ref} from 'vue'
import {create as dictCreateApi} from "../../api/admin/dictAdminApi"
import {batchAddPageFormItems} from "../../components/admin/dictManage";
import {clone} from "../../../../../global/common/tools/ObjectTools";
import {ElMessage} from 'element-plus'
import {isString} from "../../../../../global/common/tools/StringTools";

let alert = (message,type='success')=>{
  ElMessage({
    showClose: true,
    message: message,
    type: type,
    showIcon: true,
    grouping: true
  })
}
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  parentId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    parentId: props.parentId,
  },
  // 表单数据对象
  formData: {},
})
// 批量添加的待添加内容，内容项为 {code: 'code',name: 'name',value: 'value'} 格式
const batchContentList = []
// 表单项
const formComps = ref(
    batchAddPageFormItems
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认批量添加',
  permission: 'admin:web:dict:create',
})
// 提交前处理，将批量添加内容合理化
const beforeSubmitMethod =  (form) => {
  // 清空数组
  batchContentList.splice(0, batchContentList.length)
  let batchContent = form.batchContent
  let batchContentArray = batchContent.split('\n')
  if (batchContentArray.length == 0) {
    return '批量添加内容格式不正确,未获取到数据'
  }
  let isGroup = form.isGroup
  let isItem = form.isItem
  let columnNum = 0
  if (isGroup && isItem) {
    columnNum = 3
  }else if (isGroup && !isItem) {
    columnNum = 2
  }else if (!isGroup && isItem) {
    columnNum = 2
  }else {
    return '一个字典至少为字典组或字典项'
  }
  for (let i = 0; i < batchContentArray.length; i++) {
    let batchContentArrayItemArray = batchContentArray[i].split(' ');
    if (batchContentArrayItemArray.length != columnNum) {
      return '批量添加内容格式不正确,应为 ' + columnNum + ' 列数据'
    }
    let item = batchContentArrayItemArrayToItem(batchContentArrayItemArray,isGroup,isItem)
    if (item) {
      batchContentList.push(item)
    }
  }
  return true
}
// 将按行空格分隔的字符串数组转为字典项对象
const batchContentArrayItemArrayToItem = (batchContentArrayItemArray,isGroup,isItem) => {
  if (isGroup && isItem) {
    return {code: batchContentArrayItemArray[0],name: batchContentArrayItemArray[1],value: batchContentArrayItemArray[2]}
  }else if (isGroup && !isItem) {
    return {code: batchContentArrayItemArray[0],name: batchContentArrayItemArray[1],value: null}
  }else if (!isGroup && isItem) {
    return {code: null,name: batchContentArrayItemArray[0],value: batchContentArrayItemArray[1]}
  }
  return null
}
// 提交按钮
const submitMethod = (form) => {
  let beforeResult = beforeSubmitMethod(form)
  if(beforeResult !== true){
    if (isString(beforeResult)) {
      alert(beforeResult,'error')
    }

    return false
  }
  let seq = form.seq
  const requests = batchContentList.map((item) => {
    let tempForm = clone(form)
    tempForm.code = item.code
    tempForm.name = item.name
    tempForm.value = item.value
    seq += 100
    tempForm.seq = seq
    return dictCreateApi(tempForm);
  })
  return Promise.all(requests)
}
// 成功提示语
const submitMethodSuccess = () => {
  return '批量添加成功，请刷新数据查看'
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="100"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          :layout="[3,3,3,3,3,3,1,1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
