<script setup name="BatchJsonMultipleLineParsePage" lang="ts">
/**
 * 批量按行提取json，并转换为新的json字符串
 * 主要目的是将原始的json可能是bigint类型，转换带引号的字符串类型
 */
import {getCurrentInstance, reactive, ref} from 'vue'
import {parseMultipleLine} from "../../api/front/toolsFrontApi";

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
          name: 'jsonStrLines'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: 'json多行文本',
            required: true,
            tips: '注意：每一行应该是一个完整的json字符串'
          },
          compProps: {
            clearable: true,
            rows: 10,
            type: 'textarea',
          }
        }
      },
      {
        field: {
          name: 'handleLogical',
          value: 'useBackendConvert'
        },
        element: {
          comp: 'PtRadioGroup',
          formItemProps: {
            label: '处理逻辑',
            required: true,
            tips: '后端转为方便将bigint大数值转为字符串'
          },
          compProps: {
            options: [
              {id: 'useBackendConvert',name: '使用后端接口转换'},
            ],
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
  handleLogicalMethods[form.handleLogical](form)
}

const handleLogicalMethods = {
  useBackendConvert: (form) => {

    // 表单数据
    let jsonStrLines = form.jsonStrLines.split('\n')
    let resultLines = []
    for (let i = 0; i < jsonStrLines.length; i++) {

      let resultFields = []
      let resultValues = []
      let lineStr = jsonStrLines[i]
      if (!lineStr) {
        continue
      }

      resultLines.push(lineStr)


    }
    parseMultipleLine({jsonStrs: resultLines}).then(res =>{
      let data = res.data.data
      form.text = data.join('\n')
    })

  },
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


// 成功提示语
const submitMethodSuccess = () => {
  return '提取成功'
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
