<script setup name="ReportReportApiManageTestPage" lang="ts">
/**
 * 报告接口管理测试页面
 */
import {reactive, ref} from 'vue'
import {
  detail,
  test as reportReportApiTestApi
} from "../../../api/reportapi/admin/reportReportApiAdminApi"
import {cloneObj} from "../../../../../../global/common/tools/ObjectTools";
import {ElMessage} from 'element-plus'
import {inParamTypeHandler} from "../../../../dataquery/components/datasource/admin/dataQueryDatasourceApiManage";

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
  reportReportApiId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    url: '',
  },
  // 表单数据对象
  formData: {},
  testResult: {
    value: ''
  }
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'param'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '参数',
            displayBlock: true
          },
          compProps: {
            type: 'textarea',
            rows: 15,
          }
        }
      },
      {
        field: {
          name: 'isConvertParamToObj',
          value: true
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '将参数转为对象后提交',
          },
          compProps: {
            activeText: '转为json对象',
            inactiveText: '保持字符串',
          }
        }
      },
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  permission: 'admin:web:reportReportApi:test',
  beforeMethod:()=>{
    let isConvertParamToObj = reactiveData.form.isConvertParamToObj
    let param = reactiveData.form.param
    if (isConvertParamToObj) {
      if (param) {
        try {
          param = JSON.parse(param)
        } catch (e) {
          alert('参数格式错误，请检查！','error')
          return
        }
      }
    }
    return true
  }
})
// 提交按钮
const submitMethod = (form) => {

  let data = cloneObj(form)
  if (data.isConvertParamToObj) {
    if (data.param) {
      try {
        data.param = JSON.parse(data.param)
      } catch (e) {
        alert('参数格式错误，请检查！','error')
        return
      }
    }
  }
  return reportReportApiTestApi(data);
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detail({id: props.reportReportApiId}).then((res) => {
    reactiveData.form.param = res.data.data.inParamExampleConfigJson
    return Promise.resolve(res)
  })
}
// 成功提示语
const submitMethodSuccess = (res) => {
  let data = res.data
  reactiveData.testResult.value = JSON.stringify(data,null,'   ')
  return '数据查询成功'
}
const onResetForm = ()=>{
  reactiveData.testResult.value = ''
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="100"
          :dataMethod="dataMethod"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          :onResetForm="onResetForm"
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>
  <el-text type="warning">注意：如有修改模板或接口请刷新缓存后再测试</el-text>

  <PtAceEditor v-model="reactiveData.testResult.value" mode="ace/mode/json" class="pt-width-100-pc"></PtAceEditor>

</template>


<style scoped>

</style>
