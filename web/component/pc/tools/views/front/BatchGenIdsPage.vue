<script setup name="BatchGenIdsPage" lang="ts">
/**
 * 批量生成id
 */
import {getCurrentInstance, reactive, ref} from 'vue'
import {batchGenIds} from "../../api/front/toolsFrontApi";

const { proxy } = getCurrentInstance()
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  resultForm: {result: ''},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'num',
          value: 100
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '生成数量',
            required: true
          },
          compProps: {
          }
        }
      },

    ]
)
const resultFormComps= ref(
    [
      {
        field: {
          name: 'result'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '生成结果',
          },
          compProps: {
            clearable: true,
            type: 'textarea',
            rows: 25
          }
        }
      },

    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认生成',
})
// 提交按钮
const submitMethod = () => {
  return batchGenIds
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
const submitMethodSuccess = (res) => {
  let dataList = res.data.data
  reactiveData.resultForm.result = dataList.join('\n')
  return '生成成功'
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :comps="formComps">
  </PtForm>
  <!-- 结果表单 -->
  <PtForm :form="reactiveData.resultForm"
          labelWidth="80"
          defaultButtonsShow=""
          :layout="1"
          :comps="resultFormComps">
  </PtForm>
</template>


<style scoped>

</style>
