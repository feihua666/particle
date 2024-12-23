<script setup name="DataQueryDataApiManageTestPage" lang="ts">
/**
 * 数据查询数据源接口管理接口测试页面
 */
import {nextTick, reactive, ref} from 'vue'
import {
  apiTest as dataQueryDataApiTestApi,
  ApiTestParam,
  detail as dataQueryDataApiDetailApi,
} from "../../../api/dataapi/admin/dataQueryDataApiAdminApi.ts"

import {ElMessage} from 'element-plus'
import {inParamTypeHandler} from "../../../components/datasource/admin/dataQueryDatasourceApiManage";
import {detail as dataQueryDatasourceApiDetailApi} from "../../../api/datasource/admin/dataQueryDatasourceApiAdminApi";

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
  dataQueryDataApiId: {
    type: String,
    required: true
  },
})
// 入参类型
const inParamType = ref('')
// 接口地址
const url = ref('')
// 属性
const reactiveData = reactive({
  form: {
    param: ''
  },
  formComps: [
    {
      field: {
        name: 'name',
        valueChange({form,formData}){
          nextTick(()=>{
           if(formData.name){
             form.param = formData.name.content
           }
         })

        }
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '用例名称',
          tips: '选择一个用例数据作为参数，默认选中第一个'
        },
        compProps: {
          clearable: true,
          // 匹配第一个默认选中
          defaultValueItem: (item) => true,
          props: {
            value: 'name'
          },
          dataMethod: ()=> {
            return dataQueryDataApiDetailApi({id: props.dataQueryDataApiId})
            .then(res=>{
              let detail = res.data.data
              url.value = detail.url
              // 如果是一对一直连，直接使用数据源接口数据
              let adaptTypeDictValue = detail.adaptTypeDictValue
              let r = handleTestCasesData(detail)
              if ('single_direct' != adaptTypeDictValue) {
                return Promise.resolve(r)
              }else {
                // 如果一对一直连自己配置有值，直接使用
                // 如果一对一直连自己配置没有值，则使用数据源接口配置
                return dataQueryDatasourceApiDetailApi({id: detail.dataQueryDatasourceApiId}).then(response =>{
                  let detailSingle_direct = response.data.data
                  // 如果自己配置了就使用自己的
                  if(detail.inParamTypeDictValue){
                    detailSingle_direct.inParamTypeDictValue = detail.inParamTypeDictValue
                  }
                  if(detail.inParamTestCaseDataConfigJson){
                    detailSingle_direct.inParamTestCaseDataConfigJson = detail.inParamTestCaseDataConfigJson
                  }
                  let r = handleTestCasesData(detailSingle_direct)
                  return Promise.resolve(r)
                })
              }

            })
          }
        }
      }
    },
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
  ],
  testResult: {
    value: ''
  }
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  permission: 'admin:web:dataQueryDataApi:test',
  beforeMethod:()=>{
    let param = reactiveData.form.param
    if (inParamType.value) {
      if(!param){
        alert('接口需要请求参数，请填写','error')
        return
      }
      try {
        param = inParamTypeHandler[inParamType.value](param)
      } catch (e) {
        alert('参数格式错误，请检查！','error')
        return
      }
    }
    return true
  }
})

// 查询按钮
const submitMethod = (form) => {
  let param = form.param
  if (inParamType.value) {
    param = inParamTypeHandler[inParamType.value](param)
  }
  // 不需要参数，直接设置为空
  else {
    param = null
  }
  let data:ApiTestParam = {
    url: url.value,
    param: param
  }
  if ('queryString' == inParamType.value) {
    data = {
      url: url.value,
      queryString: param
    }
  }
  return dataQueryDataApiTestApi(data)
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

const handleTestCasesData = (detail)=>{
  // 利用一下返回值，在测试接口时根据入参类型
  inParamType.value = detail.inParamTypeDictValue

  let r = []
  if(detail.inParamTestCaseDataConfigJson){
    let inParamTestCaseDataConfigJsonObj = JSON.parse(detail.inParamTestCaseDataConfigJson)
    r = inParamTestCaseDataConfigJsonObj.inParamTestCases
  }
  return r
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          labelWidth="100"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"

          :onResetForm="onResetForm"
          inline
          :layout="1"
          :comps="reactiveData.formComps">
  </PtForm>


  <PtAceEditor v-model="reactiveData.testResult.value" mode="ace/mode/json" class="pt-width-100-pc"></PtAceEditor>
</template>


<style scoped>

</style>
