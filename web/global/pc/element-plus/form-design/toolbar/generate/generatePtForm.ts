/**
 * 脚本生成
 */
const generateScript = (comps,props)=>{
    const result = `
<script setup name="PtForm" lang="ts">
/**
 * PtForm 表单自动生成
 */
import {reactive, ref} from 'vue'

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    ${JSON.stringify(comps,(key,value)=>value === null ? undefined : value, '  ')}
)
const formProps = ref(
    ${JSON.stringify(props,(key,value)=>value === null ? undefined : value, '  ')}
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '提交表单',
  // 可以指定权限
  // permission: 'updateUserNickname',
})
// 提交按钮
const submitMethod = () => {
    // 可以在方法体内直接写逻辑，或返回一个promise
    console.log('表单提交成功')
  return
}
// 成功提示语
const submitMethodSuccess = () => {
  // 提交成功提示信息
  return '添加成功，请刷新数据查看'
}
</script>
    `

    return result
}
/**
 * 模板生成
 */
const generateTemplate = () => {
    const result = `
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :comps="formComps"
          v-bind="formProps"
          >
  </PtForm>
</template>
    `
    return result
}
/**
 * 样式生成
 */
const generateStyle = () => {
    return `
<style scoped>

</style>
    `
}

export const generatePtForm = (comps,props) => {

    return generateScript(comps,props) + generateTemplate() + generateStyle()
}