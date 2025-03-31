<script setup name="DeleteModelServicePage" lang="ts">
/**
 * 删除模型服务，主要是对使用低代码生成的模型服务
 */
import {getCurrentInstance, reactive, ref} from 'vue'
import {underlineToHump} from "../../../../../global/common/tools/StringTools";
import {addField, deleteModelService} from "../../api/front/toolsFrontApi";

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
          name: 'domainName'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '领域模型名称',
            required: true,
          },
          compProps: {
            clearable: true,
            placeholder: '领域模型名称，一般为类名称'
          }
        }
      },
      {
        field: {
          name: 'componentBackendAbsolutePath'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '后端组件绝对路径',
            required: true,
            tips: '如：/Users/yw/fh/git-source/particle/component/tenant'
          },
          compProps: {
            clearable: true,
            placeholder: '如：/Users/yw/fh/git-source/particle/component/tenant'
          }
        }
      },
      {
        field: {
          name: 'componentFrontendAbsolutePath'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '前端组件绝对路径',
            required: true,
            tips: '如：/Users/yw/fh/git-source/particle/web/component/pc/tenant'
          },
          compProps: {
            clearable: true,
            placeholder: '如：/Users/yw/fh/git-source/particle/web/component/pc/tenant'
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认删除',
})
// 提交按钮
const submitMethod = (form) => {
  return deleteModelService(form)
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
  return '删除成功'
}
</script>
<template>
  <!-- 删除表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="150"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :layout="1"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>
