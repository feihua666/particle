<script setup name="OpenplatformProviderRecordManageParamViewPage" lang="ts">
/**
 * 开放平台开放接口供应商调用记录管理参数查看页面
 */
import {reactive, ref} from 'vue'
import {
  paramDetailByOpenplatformProviderRecordId
} from "../../../api/providerrecord/admin/openplatformProviderRecordAdminApi"

import {paramViewPageFormItems} from "../../../components/providerrecord/admin/openplatformProviderRecordManage";


// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  openplatformProviderRecordId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.openplatformProviderRecordId,
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
  return paramDetailByOpenplatformProviderRecordId({id: props.openplatformProviderRecordId}).then(res => {
    let data = res.data.data
    try {
      let requestParam = data.requestParam
      if (requestParam) {
        let requestParamObj = JSON.parse(requestParam)
        data['requestParam'] = JSON.stringify(requestParamObj, null, '   ');
      }
    }catch (e) {

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