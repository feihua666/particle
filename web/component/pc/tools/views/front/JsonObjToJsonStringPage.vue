<script setup name="JsonObjToJsonStringPage" lang="ts">
/**
 * json对象转为json字符串，该功能具体风险，因为具体代码执行能力
 */
import {reactive,getCurrentInstance, ref} from 'vue'
import {lowerFirst, replace, upperFirst} from "../../../../../global/common/tools/StringTools";
import {loadScriptCode} from "../../../../../global/common/tools/DocumentTools";

const { proxy } = getCurrentInstance()
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'jsonObjStr'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'json对象文本',
            required: true
          },
          compProps: {
            clearable: true,
            rows: 10,
            type: 'textarea',
            placeholder: '输入json对象，一般为前端的json对象不带双引号。如：{user: 1,gender: \'男\'}'
          }
        }
      },
      {
        field: {
          name: 'text'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '结果'
          },
          compProps: {
            clearable: true,
            readonly: true,
            placeholder: '结果这里显示完成的结果,输出为标准的带双引号json字符串。如：{"user": 1,gender: "男"}',
            rows: 10,
            type: 'textarea'
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认转换',
})
// 提交按钮
const submitMethod = (form) => {
  // 表单数据
  let jsonObjStr = form.jsonObjStr
  loadScriptCode("window.jsonObjToJsonString = " + jsonObjStr)

  form.text = JSON.stringify(window.jsonObjToJsonString,null,'  ')
}
const alert = (message) =>{
  proxy.$message({
    showClose: true,
    message: message,
    type: 'error',
    showIcon: true,
    grouping: true
  })
}
const doReplace = (text: string,from: string,to: string,upperAndLower?: boolean): string => {
  let result = text
  if (upperAndLower) {
    result = replace(result,upperFirst(from),upperFirst(to),true)
    result = replace(result,lowerFirst(from),lowerFirst(to),true)
  }else {
    result = replace(result,from,to,true)
  }
  return result
}

// 成功提示语
const submitMethodSuccess = () => {
  return '转换成功'
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :layout="1"
          label-position="top"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>