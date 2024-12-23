<script setup name="OpenplatformOpenapiDataQueryPage" lang="ts">
/**
 * 开放平台开放接口查询页面
 */
import {reactive, ref} from 'vue'
import {singleQuery,} from "../../../api/openapi/admin/openplatformOpenapiAdminApi"
import {useCascaderOpenapiByOpenplatformAppIdCompItem,} from "../../../components/openplatformOpenapiCompItem";
import {useSelectAppForCurrentUserCompItem} from "../../../components/openplatformAppCompItem";
import {allDetail} from "../../../api/doc/admin/openplatformDocApiAdminApi";
import {isEmpty} from "../../../../../../global/common/tools/ObjectTools";


// form 引用
const formRef = ref(null)
const dynamicFormRef = ref(null)
const resultFormRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    useSelectAppForCurrentUserCompItem({
      label: '应用',
      required: true,
      valueChange: ({form,newValue}) => {
        if (!newValue) {
          form.openplatformOpenapiId = null
        }
      },
      tips: ' 1. 请先选择要使用的应用'
    }),
    useCascaderOpenapiByOpenplatformAppIdCompItem({
      fieldName: 'openplatformOpenapiId',
      label: '开放接口',
      required: true,
      disableGroup: true,
      valueChange: ({form,newValue}) => {
        loadAllDetail(newValue)
      },
      tips: ' 2. 请选择要查询的接口'
    }),

  ],
  dynamicform: {},
  dynamicformComps:[],
  dynamicformCompsReady: false,
  dynamicformLoading: false,

  resultForm: {result: ''},
  resultFormComps:[
    {
      field: {
        name: 'result'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '调用结果',
        },
        compProps: {
          clearable: true,
          type: 'textarea',
          rows: 25,
          readonly: true,
          placeholder: '该区域显示查询结果，为只读状态，点击重置会清空该区域数据'
        }
      }
    },
  ],
  openplatformDocApiAllDetail: null
})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:openplatformOpenapi:singleQuery'
})
// 查询按钮
const submitMethod = ():void => {
  validateForm(()=>{
    validateDynamicForm(()=>{
      submitAttrs.value.loading = true
      doOpenplatformOpenapiSingleQuery()
          .then(res =>{
            let dataResult = res.data.data
            try {
              let dataJson = JSON.parse(dataResult)
              let dataJsonStr = JSON.stringify(dataJson,null,'   ')
              reactiveData.resultForm.result = dataJsonStr
            }catch (e) {
              reactiveData.resultForm.result = dataResult
            }
          }).finally(()=> {
            submitAttrs.value.loading = false
          })
    })
  })
}
const validateForm = (callback) =>{
  if (formRef.value) {
    formRef.value.formRef.validate((valid, fields) => {
      if (valid) {
        callback()
      }
    })
  }
}
const validateDynamicForm = (callback) => {
  if (dynamicFormRef.value) {
    dynamicFormRef.value.formRef.validate((valid, fields) => {
      if (valid) {
        callback()
      }
    })
  }
}
// 数据查询
const doOpenplatformOpenapiSingleQuery = () => {
  let dataForm: any = {
    // 开放接口请求体参数 后端：Map<String,Object>
    bodyParam: null,
    // 开放接口请求查询字符串参数 后端：Map<String,String>
    queryStringParam: null,
  }
  for (let formKey in reactiveData.form) {
    dataForm[formKey] = reactiveData.form[formKey]
  }
  // body 参数
  let bodyParam = {}
  for (let dynamicformKey in reactiveData.dynamicform) {
    bodyParam[dynamicformKey] = reactiveData.dynamicform[dynamicformKey]
  }
  if(!isEmpty(bodyParam)){
    dataForm.bodyParam = bodyParam
  }

  // queryString 参数暂不支持

  return singleQuery(dataForm)
}
/**
 * 加载接口文档详情
 * @param openplatformOpenapiId
 */
const loadAllDetail =  (openplatformOpenapiId) => {
  reactiveData.dynamicformCompsReady = false
  if (!openplatformOpenapiId) {
    return
  }
  reactiveData.dynamicformLoading = true
  allDetail({openplatformOpenapiId: openplatformOpenapiId}).then(res => {
    reactiveData.openplatformDocApiAllDetail = res.data
    handleDynamicForm()
  }).catch(err => {
    reactiveData.openplatformDocApiAllDetail = null
    handleDynamicForm()
  }).finally(() => {
    reactiveData.dynamicformLoading = false
    reactiveData.dynamicformCompsReady = true
  });
}
// 根据接口文档详情组件动态表单
const handleDynamicForm = ()=>{
  reactiveData.dynamicform = {}
  reactiveData.dynamicformComps = []
  if (!reactiveData.openplatformDocApiAllDetail) {
    return;
  }
  let data = reactiveData.openplatformDocApiAllDetail.data;
  if (isEmpty(data)) {
    return
  }
  let docApiDocParamFieldVOsMap = data.docApiDocParamFieldVOsMap
  // 请求参数
  let request_params = docApiDocParamFieldVOsMap.request_param
  for (let i = 0; i < request_params.length; i++) {
    let request_param = request_params[i]
    // 只支持一级参数
    if (request_param.parentId) {
      continue
    }
    reactiveData.dynamicformComps.push(
        {
          field: {
            name: request_param['name'],
          },
          element: {
            comp: 'el-input',
            formItemProps: {
              label: request_param['explanation'],
              required: request_param['isRequired'],
            },
            compProps: {
              clearable: true
            }
          }
        },
    );
  }

}

const buttonFormOnResetForm = ()=>{
  // if (formRef.value) {
  //   formRef.value.resetForm()
  // }
  if (dynamicFormRef.value) {
    dynamicFormRef.value.resetForm()
  }
  if (resultFormRef.value) {
    resultFormRef.value.resetForm()
  }
}

</script>
<template>
  <!-- 查询表单 -->
  <PtForm ref="formRef" :form="reactiveData.form"
          labelWidth="120"
          defaultButtonsShow=""
          inline
          :layout="2"
          :comps="reactiveData.formComps">
  </PtForm>
  <div v-loading="reactiveData.dynamicformLoading">
    <!-- 动态查询表单 -->
    <PtForm ref="dynamicFormRef"  v-if="reactiveData.dynamicformCompsReady" :form="reactiveData.dynamicform"
             labelWidth="120"
             defaultButtonsShow=""
             inline
             :comps="reactiveData.dynamicformComps">
    </PtForm>
  </div>

<!-- 按钮表单 -->
  <PtForm  :form="[]"
           labelWidth="120"
           :method="submitMethod"
           defaultButtonsShow="submit,reset"
           :submitAttrs="submitAttrs"
           inline
           :showButtonItem="true"
           :onResetForm="buttonFormOnResetForm"
           :comps="[]">
  </PtForm>
  <!-- 结果表单 -->
  <PtForm ref="resultFormRef" :form="reactiveData.resultForm"
          labelWidth="80"
          defaultButtonsShow=""
          :layout="1"
          :comps="reactiveData.resultFormComps">
  </PtForm>

</template>


<style scoped>

</style>
