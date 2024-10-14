<script setup name="OpenplatformOpenapiRecordManageParamViewPage" lang="ts">
/**
 * 开放平台开放接口调用记录参数查看页面
 */
import {reactive, ref} from 'vue'
import {paramDetailByOpenplatformOpenapiRecordId} from "../../../api/openapirecord/admin/openplatformOpenapiRecordAdminApi"

import {paramViewPageFormItems} from "../../../components/openapirecord/admin/openplatformOpenapiRecordManage";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  openplatformOpenapiRecordId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.openplatformOpenapiRecordId,
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    paramViewPageFormItems
)

// 初始化加载更新的数据
const dataMethod = () => {
  return paramDetailByOpenplatformOpenapiRecordId({id: props.openplatformOpenapiRecordId}).then(res => {
    let data = res.data.data
    let requestParam = data.requestParam
    let requestParamObj = JSON.parse(requestParam)

    let mapping = {
      pathParameters: 'requestPathParam',
      queryParameters: 'requestQueryString',
      formParameters: 'requestFormParam',
      bodyParameters: 'requestBodyParam',
    }
    for (let mappingKey in mapping) {
      try {
        let mappingValue = mapping[mappingKey]
        let requestParamObjValue = requestParamObj[mappingKey]
        // 先设置默认值
        data[mappingValue] = requestParamObjValue

        // 加一个判断，否则 null 值会转为 null 字符串
        // 尝试格式化
        if (requestParamObjValue) {
          // 尝试转json,并设置值，有异常后将使用上面默认的
          let jsonValue = JSON.parse(requestParamObjValue);
          let jsonValueStr = JSON.stringify(jsonValue, null, '   ')
          data[mappingValue] = jsonValueStr
        }

      }catch (e) {

      }
    }
    try {
      let responseResult = data.responseResult
      if (responseResult) {
        let responseResultObj = JSON.parse(responseResult)
        data['responseResult'] = JSON.stringify(responseResultObj, null, '   ');
      }
    }catch (e) {

    }


    return Promise.resolve(res)
  })
}


</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :dataMethod="dataMethod"
          defaultButtonsShow=""
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="1"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>