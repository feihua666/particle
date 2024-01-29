<script setup name="DataQueryDatasourceApiManageTestPage" lang="ts">
/**
 * 数据查询数据源接口管理接口测试页面
 */
import {reactive, ref,nextTick} from 'vue'
import {
  apiTest as dataQueryDatasourceApiTestApi, ApiTestParam, detail as dataQueryDatasourceApiDetailApi,
} from "../../../api/datasource/admin/dataQueryDatasourceApiAdminApi"

import {ElMessage} from 'element-plus'
import {inParamTypeHandler, paramType} from "../../../compnents/datasource/admin/dataQueryDatasourceApiManage";

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
  dataQueryDatasourceApiId: {
    type: String,
    required: true
  },
})
// 入参类型
const inParamType = ref('')
// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
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
             if('queryString' === inParamType.value){
               form.queryString = formData.name.content
             }else {
               form.param = formData.name.content
             }
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
            return dataQueryDatasourceApiDetailApi({id: props.dataQueryDatasourceApiId})
            .then(res=>{
              let detail = res.data.data
              // 利用一下返回值，在测试接口时根据入参类型
              inParamType.value = detail.inParamTypeDictValue
              let r = []
              if(detail.inParamTestCaseDataConfigJson){
                let inParamTestCaseDataConfigJsonObj = JSON.parse(detail.inParamTestCaseDataConfigJson)
                r = inParamTestCaseDataConfigJsonObj.inParamTestCases
              }
              return Promise.resolve(r)
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
    {
      field: {
        name: 'queryString'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '查询字符串',
          displayBlock: true
        },
        compProps: {
          clearable: true,
          placeholder: '仅http支持'
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
  permission: 'admin:web:dataQueryDatasourceApi:test',
  beforeMethod:()=>{
    let param = reactiveData.form.param
    let queryString = reactiveData.form.queryString
    if (inParamType.value) {
      if(!param && !queryString){
        alert('接口需要请求参数，请填写','error')
        return
      }
      try {
        // 尝试解析一下，如果不正确，给出提示
        // 如果数据查询参数有值，就尝试解析
        if (queryString) {
          inParamTypeHandler['queryString'](queryString)
        }

      } catch (e) {
        alert('数据查询参数格式错误，接口定义的入参类型和输入参数转换错误，请检查！','error')
        return
      }
      try {

        // 如果查询参数有值，且不是入参
        if (param) {
          if ('queryString' != inParamType.value) {
            // 尝试解析一下，如果不正确，给出提示
            inParamTypeHandler[inParamType.value](param);
          }
        }
      } catch (e) {
        alert('参数格式错误，接口定义的入参类型和输入参数转换错误，请检查！','error')
        return
      }
    }
    return true
  }
})

// 查询按钮
const submitMethod = (form) => {
  let param = form.param
  let queryString = form.queryString
  let data:ApiTestParam = {
    dataQueryDatasourceApiId: props.dataQueryDatasourceApiId,
    param: null,
    queryString: queryString || null
  }

  if (inParamType.value) {
    if ('queryString' != inParamType.value) {
      data.param = inParamTypeHandler[inParamType.value](param)
    }else {
    // 全部解析一下，哪个对，用哪个
    //   对象
      try {
        data.param = inParamTypeHandler[paramType.object](param)
      } catch (e) {}
      // 数组
      if (!data.param) {
        try {
          data.param = inParamTypeHandler[paramType.array](param)
        } catch (e) {}
      }
      // 浮点数
      if (!data.param) {
        try {
          data.param = inParamTypeHandler[paramType.float](param)
        } catch (e) {}
      }
      // 数字
      if (!data.param) {
        try {
          data.param = inParamTypeHandler[paramType.number](param)
        } catch (e) {}
      }
      // 布尔
      if (!data.param) {
        try {
          data.param = inParamTypeHandler[paramType.boolean](param)
        } catch (e) {}
      }
      // 字符串
      if (!(data.param === true || data.param === false)) {
        try {
          data.param = inParamTypeHandler[paramType.string](param)
        } catch (e) {}
      }
    }

  }


  return dataQueryDatasourceApiTestApi(data);
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